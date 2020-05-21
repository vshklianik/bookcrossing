package by.serhel.springwebapp.entities;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.*;
import java.util.stream.Collectors;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

//    @NotBlank(message = "Please fill the book name")
//    @Length(max = 255, message = "Book name to long")
    private String bookName;
//    @Length(max = 255, message = "Author name to long")
    private String authorName;

//    private String genre;
    @ElementCollection(targetClass = GenreType.class, fetch = FetchType.LAZY)
    @CollectionTable(name = "genre", joinColumns = @JoinColumn(name = "book_id"))
    @Enumerated(EnumType.STRING)
    private Set<GenreType> genre;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    private String filename;

    public Book() {
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getAuthorUsername(){
        return author != null ? author.getUsername() : "<none>";
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Set<GenreType> getGenre() {
        return genre;
    }

    public void setGenre(Set<GenreType> genre) {
        this.genre = genre;
    }

    public List<String> getListOfGenre() {
        List<String> list = new ArrayList<>();
        for(GenreType g : genre){
            list.add(g.getValue());
        }
        return list;
    }
}