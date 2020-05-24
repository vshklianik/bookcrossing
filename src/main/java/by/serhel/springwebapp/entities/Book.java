package by.serhel.springwebapp.entities;

import by.serhel.springwebapp.entities.types.GenreType;

import javax.persistence.*;
import java.util.*;

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

    @ElementCollection(targetClass = GenreType.class, fetch = FetchType.LAZY)
    @CollectionTable(name = "genre", joinColumns = @JoinColumn(name = "book_id"))
    @Enumerated(EnumType.STRING)
    private Set<GenreType> genre;

    @ManyToOne(fetch = FetchType.LAZY)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id) &&
                Objects.equals(bookName, book.bookName) &&
                Objects.equals(authorName, book.authorName) &&
                Objects.equals(genre, book.genre) &&
                Objects.equals(author, book.author) &&
                Objects.equals(filename, book.filename);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bookName, authorName, genre, author, filename);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", authorName='" + authorName + '\'' +
                ", genre=" + genre +
                ", author=" + author +
                ", filename='" + filename + '\'' +
                '}';
    }
}