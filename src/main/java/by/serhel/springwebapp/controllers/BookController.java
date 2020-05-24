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
        model.addAttribute("books", bookService.findByAuthor(user));
        logger.info("finish 'getMyBooks'");
        return "myBooks";
    }

    @PostMapping()
    public String add(@AuthenticationPrincipal User user,
                      Book book,
                      @RequestParam Map<String, String> form,
                      BindingResult bindingResult,
                      Model model,
                      @RequestParam("file") MultipartFile file) throws IOException
    {
        logger.info(" start 'add'");
        book.setAuthor(user)  ;

//        if(bindingResult.hasErrors()){
//            Map<String, String> errorMap = ControllerUtils.getErrors(bindingResult);
//            model.mergeAttributes(errorMap);
//            model.addAttribute("book", book);
//        }
//        else {
            book.setFilename(bookService.addFile(file));
//            model.addAttribute("book", null);
            bookService.saveBook(book, form);
//        }

        model.addAttribute("books", bookService.findByAuthor(user));
        logger.info("finish 'add'");
        return "redirect:/mybooks";
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
                           Book book,
                           Model model,
                           @RequestParam Map<String, String> form,
                           @RequestParam("file") MultipartFile file) throws IOException
    {
        logger.info("start 'saveBook'");
        book.setAuthor(user);
        book.setFilename(bookService.saveFile(book, file));
        bookService.saveBook(book, form);
//        model.addAttribute("message", "Save book is successfully.");
        model.addAttribute("books", bookService.findByAuthor(user));
        logger.info("finish 'saveBook'");
        return "redirect:/mybooks";
    }

    @GetMapping("/delete/{book}")
    public String deleteBook(@PathVariable Book book,
                             Model model) {
        logger.info("start 'deleteBook'");
        bookService.deleteBook(book);
//        model.addAttribute("message", "Delete book is successfully.");
        logger.info("finish 'deleteBook'");
        return "redirect:/mybooks";
    }
}
