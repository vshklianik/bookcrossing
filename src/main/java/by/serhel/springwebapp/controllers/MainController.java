package by.serhel.springwebapp.controllers;

import by.serhel.springwebapp.entities.Book;
import by.serhel.springwebapp.entities.User;
import by.serhel.springwebapp.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
public class MainController {
    @Autowired
    private BookRepository bookRepository;

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
        Iterable<Book> adverts;
        if(filter != null && !filter.isEmpty()){
            adverts = bookRepository.findByGenre(filter);
        }
        else{
            adverts = bookRepository.findAll();      }

        model.addAttribute("adverts", adverts);
        model.addAttribute("filter", filter);

        return "main";
    }

    @PostMapping("/main")
    public String add(@AuthenticationPrincipal User user,
                      @Valid Book book,
                      BindingResult bindingResult,
                      Model model,
                      @RequestParam("file") MultipartFile file) throws IOException
    {
        book.setAuthor(user)  ;

        if(bindingResult.hasErrors()){
            Map<String, String> errorMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorMap);
            model.addAttribute("advert", book);
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

                book.setFilename(resultFileName);
            }
            model.addAttribute("advert", null);
            bookRepository.save(book);
        }
        Iterable<Book> adverts = bookRepository.findAll();
        model.addAttribute("adverts", adverts);
        return "main";
    }

    @GetMapping("/myBooks")
    public String getMyBooks(@AuthenticationPrincipal User user, Model model){
        model.addAttribute("user", user);
        model.addAttribute("books", bookRepository.findByAuthor(user));
        return "myBooks";
    }
}
