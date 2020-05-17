package by.serhel.springwebapp.service;

import by.serhel.springwebapp.entities.Book;
import by.serhel.springwebapp.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

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

    public void saveBook(Book book){
        bookRepository.save(book);
    }
}
