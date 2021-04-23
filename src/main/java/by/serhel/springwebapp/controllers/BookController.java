package by.serhel.springwebapp.controllers;

import by.serhel.springwebapp.entities.Book;
import by.serhel.springwebapp.entities.types.GenreType;
import by.serhel.springwebapp.entities.User;
import by.serhel.springwebapp.service.BookService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("/mybooks")
public class BookController {
    private static Logger logger = LogManager.getLogger(BookController.class.getName());

    @Autowired
    private BookService bookService;

    @GetMapping()
    public String getMyBooks(@AuthenticationPrincipal User user, Model model){
        logger.info("start 'getMyBooks'");
        model.addAttribute("genres", GenreType.values());
        model.addAttribute("user", user);
        model.addAttribute("books", bookService.getBooksByAuthor(user));
        logger.info("finish 'getMyBooks'");
        return "myBooks";
    }

    @PostMapping()
    public String add(@AuthenticationPrincipal User user,
                      @RequestParam("file") MultipartFile file,
                      @RequestParam Map<String, String> form,
                      @Valid Book book,
                      BindingResult bindingResult,
                      Model model) throws IOException
    {
        logger.info(" start 'add'");
        if (bindingResult.hasErrors()) {
            book.setAuthor(user);
            model.addAttribute("bookError", book);
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
        } else {
            model.addAttribute("book", null);
            bookService.saveBook(user, file, book, form);
        }
        model.addAttribute("genres", GenreType.values());
        model.addAttribute("user", user);
        model.addAttribute("books", bookService.getBooksByAuthor(user));
        logger.info("finish 'add'");
        return "myBooks";
    }

    @GetMapping("/edit/{book}")
    public String editBook(@PathVariable Book book, Model model){
        logger.info("start 'editBook'");

        model.addAttribute("genres", GenreType.values());
        model.addAttribute("book", book);
        logger.info("finish 'editBook'");
        return "bookEdit";
    }

    @PostMapping("edit/")
    public String saveBook(@AuthenticationPrincipal User user,
                           @Valid Book book,
                           BindingResult bindingResult,
                           Model model,
                           @RequestParam Map<String, String> form,
                           @RequestParam("file") MultipartFile file) throws IOException
    {
        logger.info("start 'saveBook'");
        model.addAttribute("genres", GenreType.values());
        if(bindingResult.hasErrors()) {
            model.addAttribute("bookError", book);
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            return "bookEdit";
        }
        bookService.saveBook(user, file, book, form);
        logger.info("finish 'saveBook'");
        return "redirect:/mybooks";
    }

    @GetMapping("/delete/{book}")
    public String deleteBook(@PathVariable Book book,
                             Model model) {
        logger.info("start 'deleteBook'");
        bookService.deleteBook(book);
        logger.info("finish 'deleteBook'");
        return "redirect:/mybooks";
    }
}