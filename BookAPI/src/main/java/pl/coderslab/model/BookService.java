package pl.coderslab.model;

import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> getBooks();
    void addBook(Book book);
    Optional<Book> get(Long id);
    Optional<Book> editBook(Long id);
    void deleteBook(Long id);
}
