package by.serhel.springwebapp.controllers;

import by.serhel.springwebapp.entities.Advert;
import by.serhel.springwebapp.entities.User;
import by.serhel.springwebapp.repositories.AdvertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
public class MainController {
    @Autowired
    private AdvertRepository advertRepository;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/")
    public String homePage(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("authentication", authentication);
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
                      @RequestParam String bookName,
                      @RequestParam String authorName,
                      @RequestParam String genre, Map<String, Object> model,
                      @RequestParam("file") MultipartFile file) throws IOException {
        Advert advert = new Advert(bookName, authorName, genre, user);

        if(file != null && !file.getOriginalFilename().isEmpty()){
            File uploadDir = new File(uploadPath);

            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + resultFileName));

            advert.setFilename(resultFileName);
        }

        advertRepository.save(advert);
        Iterable<Advert> adverts = advertRepository.findAll();
        model.put("adverts", adverts);
        return "main";
    }
}
