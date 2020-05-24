package by.serhel.springwebapp.controllers;

import by.serhel.springwebapp.entities.Book;
import by.serhel.springwebapp.entities.User;
import by.serhel.springwebapp.service.BookService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    private static Logger logger = LogManager.getLogger(MainController.class.getName());

    @Autowired
    private BookService bookService;

    @GetMapping("/")
    public String homePage(Model model)
    {
        logger.info("return 'homePage'");
        return "home";
    }

    @GetMapping("/books")
    public String main(@RequestParam(required = false) String filter, Model model){
        logger.info("start 'main'");
        Iterable<Book> adverts;
        if(filter != null && !filter.isEmpty()){
            adverts = bookService.getBooksByGenre(filter);
        }
        else{
            adverts = bookService.getAllBooks();
        }

        model.addAttribute("books", adverts);
        model.addAttribute("filter", filter);

        logger.info("finish 'main'");
        return "Books";
    }

    @GetMapping("/books/{book}")
    public String getUserProfile(@PathVariable Book book, Model model){
        logger.info("start 'getUserProfile'");

        model.addAttribute("userInfo", book.getAuthor());
        model.addAttribute("book", book);

        logger.info("finish 'getUserProfile'");
        return "MoreInfoOfBook";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("books/delete/{book}")
    public String deleteBook(@PathVariable Book book, Model model){
        logger.info("start 'deleteBook'");

        bookService.deleteBook(book);
        model.addAttribute("message", "success");

        logger.info("finish 'deleteBook'");
        return "redirect:/books";
    }
}
