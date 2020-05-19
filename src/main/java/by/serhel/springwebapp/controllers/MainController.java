package by.serhel.springwebapp.controllers;

import by.serhel.springwebapp.entities.Book;
import by.serhel.springwebapp.repositories.BookRepository;
import by.serhel.springwebapp.service.BookService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    private static Logger logger = LogManager.getLogger(MainController.class.getName());

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/")
    public String homePage(Model model)
    {
        logger.info("return 'homePage'");
        return "home";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false) String filter, Model model){
        logger.info("start 'main'");
        Iterable<Book> adverts;
        if(filter != null && !filter.isEmpty()){
            adverts = bookRepository.findByGenre(filter);
        }
        else{
            adverts = bookRepository.findAll();      }

        model.addAttribute("books", adverts);
        model.addAttribute("filter", filter);

        logger.info("finish 'main'");
        return "main";
    }
}
