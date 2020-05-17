package by.serhel.springwebapp.controllers;

import by.serhel.springwebapp.entities.Book;
import by.serhel.springwebapp.repositories.BookRepository;
import by.serhel.springwebapp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/")
    public String homePage(Model model){
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

        model.addAttribute("books", adverts);
        model.addAttribute("filter", filter);

        return "main";
    }
}
