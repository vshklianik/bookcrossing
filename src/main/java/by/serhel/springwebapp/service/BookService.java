package by.serhel.springwebapp.service;

import by.serhel.springwebapp.entities.Book;
import by.serhel.springwebapp.entities.GenreType;
import by.serhel.springwebapp.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Value("${upload.path}")
    private String uploadPath;

    public void deleteBook(Book book){
        bookRepository.delete(book);
    }

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

//        if(book.getGenre() != null){
//            book.getGenre().clear();
//        }
        Set<GenreType> bookGenre = new HashSet<>();

        for(String s : form.keySet()){
            if(set.contains(s)){
                bookGenre.add(GenreType.valueOf(s));
            }
        }
        book.setGenre(bookGenre);

        bookRepository.save(book);
    }

//    public String getGenresAsString(){
//        StringBuilder builder = new StringBuilder();
//        for(GenreType s : GenreType.values()){
//            builder.append(s.getValue() + ", ");
//        }
//        builder.delete(builder.length() - 1, builder.length());
//        return builder.toString();
//    }

//    public Set<String> getGenresAsSet(){
//        Set<String> values = new LinkedHashSet<>();
//            for(GenreType g : GenreType.values()){
//                System.out.println(g.getValue());
//                if(g.getValue() != null){
//                    values.add(g.getValue());
//                }
//            }
//        return values;
//    }
}
