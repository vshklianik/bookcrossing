package by.serhel.springwebapp.service;

import by.serhel.springwebapp.entities.Book;
import by.serhel.springwebapp.entities.types.GenreType;
import by.serhel.springwebapp.entities.User;
import by.serhel.springwebapp.repositories.IBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookService {
    @Autowired
    private IBookRepository bookRepository;

    @Value("${upload.path}")
    private String uploadPath;

    public static final ArrayList<String> searchedItems = new ArrayList<>();

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

    public void saveBook(User user, MultipartFile file, Book book, Map<String, String> form) throws IOException{
        book.setAuthor(user);
        book.setFilename(saveFile(book, file));
        Set<GenreType> bookGenre = getGenreTypesFromForm(form);
        book.setGenre(bookGenre);

        bookRepository.save(book);
    }

    public Set<GenreType> getGenreTypesFromForm(Map<String, String> form) {
        Set<String> set = Arrays.stream(GenreType.values()).map(GenreType::name).collect(Collectors.toSet());
        Set<GenreType> bookGenre = new HashSet<>();

        for(String s : form.keySet()){
            if(set.contains(s)){
                bookGenre.add(GenreType.valueOf(s));
            }
        }
        return bookGenre;
    }

    public void saveSearchedItems(String searchText) {
        for (int i = 0; i < 1000; i++) {
            searchedItems.add(searchText);
        }
    }

    public Iterable<Book> getBooks(String searchText, Map<String, String> genre) {
        Iterable<Book> adverts;

        Set<GenreType> bookGenre = getGenreTypesFromForm(genre);

        new BookService().saveSearchedItems(searchText);

        if(searchText != null && !StringUtils.isEmpty(searchText)){
            adverts = getBooksByName(searchText);
        }
        else if(!bookGenre.isEmpty()){
            adverts = getBooksByGenre(genre);
        }
        else{
            adverts = getAllBooks();
        }
        return adverts;
    }

    public List<Book> getBooksByAuthor(User author){
        return bookRepository.findByAuthor(author);
    }

    public List<Book> getBooksByName(String searchText){
        return bookRepository.findByBookName(searchText);
    }

    public Iterable<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public List<Book> getBooksByGenre(Map<String, String> genre){
        Iterable<Book> iterable = bookRepository.findAll();
        List<Book> books = new ArrayList<>();
        boolean check = true;
        for(Book book : iterable){
            for(String s : genre.keySet()){
                if(!book.getGenre().contains(GenreType.valueOf(s))){
                    check = false;
                }
            }
            if(check){
                books.add(book);
            }
            check = true;
        }
        return books;
    }
    public void deleteBook(Book book){
        bookRepository.delete(book);
    }

    @Transactional
    public void deleteAllBooksByAuthor(User user){
        bookRepository.deleteAllByAuthor(user);
    }
}
