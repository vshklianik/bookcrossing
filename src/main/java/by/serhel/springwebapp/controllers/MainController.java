package by.serhel.springwebapp.controllers;

import by.serhel.springwebapp.entities.Advert;
import by.serhel.springwebapp.entities.User;
import by.serhel.springwebapp.repositories.AdvertRepository;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.apache.logging.log4j.message.Message;
import org.aspectj.bridge.IMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Controller
public class MainController {
    @Autowired
    private AdvertRepository advertRepository;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/")
    public String homePage(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("isAuthenticated", authentication.isAuthenticated());
        return "home";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false) String filter, Model model){
        Iterable<Advert> adverts;
        if(filter != null && !filter.isEmpty()){
            adverts = advertRepository.findByGenre(filter);
        }
        else{
            adverts = advertRepository.findAll();      }

        model.addAttribute("adverts", adverts);
        model.addAttribute("filter", filter);

        return "main";
    }

    @PostMapping("/main")
    public String add(@AuthenticationPrincipal User user,
                      @Valid Advert advert,
                      BindingResult bindingResult,
                      Model model,
                      @RequestParam("file") MultipartFile file) throws IOException
    {
        advert.setAuthor(user)  ;

        if(bindingResult.hasErrors()){
            Map<String, String> errorMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorMap);
            model.addAttribute("advert", advert);
        }
        else {
            if (file != null && !file.getOriginalFilename().isEmpty()) {
                File uploadDir = new File(uploadPath);

                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }

                String uuidFile = UUID.randomUUID().toString();
                String resultFileName = uuidFile + "." + file.getOriginalFilename();
                file.transferTo(new File(uploadPath + resultFileName));

                advert.setFilename(resultFileName);
            }
            model.addAttribute("advert", null);
            advertRepository.save(advert);
        }
        Iterable<Advert> adverts = advertRepository.findAll();
        model.addAttribute("adverts", adverts);
        return "main";
    }
}
