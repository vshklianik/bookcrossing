package by.serhel.springwebapp.controllers;

import by.serhel.springwebapp.entities.Book;
import by.serhel.springwebapp.entities.User;
import by.serhel.springwebapp.repositories.BookRepository;
import by.serhel.springwebapp.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("/myBooks")
public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @GetMapping()
    public String getMyBooks(@AuthenticationPrincipal User user, Model model){
        model.addAttribute("user", user);
        model.addAttribute("books", bookRepository.findByAuthor(user));
        return "myBooks";
    }

    @PostMapping()
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
            model.addAttribute("book", book);
        }
        else {
            book.setFilename(bookService.addFile(file));
            model.addAttribute("book", null);
            bookService.saveBook(book);
        }
        Iterable<Book> books = bookRepository.findByAuthor(user);
        model.addAttribute("books", books);
        return "myBooks";
    }

    @GetMapping("/edit/{book}")
    public String editBook(@PathVariable Book book, Model model){
        model.addAttribute("book", book);
        return "bookEdit1";
    }

    @PostMapping("edit/")
    public String saveBook(@AuthenticationPrincipal User user,
                           Book book,
                           Model model,
                           @RequestParam("file") MultipartFile file) throws IOException
    {
        book.setAuthor(user);
        book.setFilename(bookService.saveFile(book, file));
        bookService.saveBook(book);
        model.addAttribute("message", "Save book is successfully.");
        model.addAttribute("books", bookRepository.findByAuthor(user));
        return "myBooks";
    }

    @GetMapping("/delete/{book}")
    public String deleteBook(@PathVariable Book book,
                             Model model) {
        bookService.deleteBook(book);
        model.addAttribute("message", "Delete book is successfully.");
        return "redirect:/myBooks";
    }
}
