package by.serhel.springwebapp.service;

import by.serhel.springwebapp.entities.Book;
import by.serhel.springwebapp.entities.types.GenreType;
import by.serhel.springwebapp.entities.User;
import by.serhel.springwebapp.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Transactional
@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Value("${upload.path}")
    private String uploadPath;

    public String addFile(MultipartFile file) throws IOException{
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + resultFileName));
            return resultFileName;
        }
        return "";
    }

    public String saveFile(Book book, MultipartFile file) throws IOException {
        String resultFileName = book.getFilename();
        if(file != null && !file.getOriginalFilename().isEmpty()) {
            if (book.getFilename() != null) {
                File deleteFile = new File(uploadPath + book.getFilename());
                deleteFile.delete();
            }
            resultFileName = addFile(file);
        }


        return resultFileName;
    }

    public void saveBook(Book book, Map<String, String> form){
        Set<String> set = Arrays.stream(GenreType.values()).map(GenreType::name).collect(Collectors.toSet());
        Set<GenreType> bookGenre = new HashSet<>();

        for(String s : form.keySet()){
            if(set.contains(s)){
                bookGenre.add(GenreType.valueOf(s));
            }
        }
        book.setGenre(bookGenre);

        bookRepository.save(book);
    }

    public List<Book> findByAuthor(User author){
        return bookRepository.findByAuthor(author);
    }

    public Iterable<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public List<Book> getBooksByGenre(String genre){
        return bookRepository.findByGenre(genre);
    }
    public void deleteBook(Book book){
        bookRepository.delete(book);
    }

    public void deleteAllBooksByAuthor(User user){
        bookRepository.deleteAllByAuthor(user);
    }
}
