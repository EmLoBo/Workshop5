package pl.coderslab.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Component
public class MockBookService implements BookService {
    private List<Book> list;
    private static Long nextId = 4L;


    @Override
    public List<Book> getBooks() {
        list = new ArrayList<>();
        list.add(new Book(1L, "9788324631766", "Thiniking	in	Java", "Bruce	Eckel", "Helion", "programming"));
        list.add(new Book(2L, "9788324627738", "Rusz	glowa	Java.", "Sierra	Kathy,	Bates	Bert", "Helion",
                "programming"));
        list.add(new Book(3L, "9780130819338", "Java	2.	Podstawy", "Cay	Horstmann,	Gary	Cornell", "Helion",
                "programming"));
        return list;
    }

    @Override
    public Book addBook(Book book) {

        long lastId = 0;
        for (Book b : getBooks()) {
            if (lastId < b.getId()) {
                lastId = b.getId();
            }
        }
        book.setId(++lastId);
        list.add(book);
        return book;

    }

    @Override
    public Optional<Book> get(Long id) {

        return list.stream().filter(item -> item.getId().equals(id)).findFirst();

    }


    public Optional<Book> editBook(Long id) {

        Optional<Book> book = list.stream().filter(item -> item.getId().equals(id)).findFirst();
        return null;

    }


    @Override
    public void deleteBook(Long id) {

            Optional<Book> first = list.stream().filter(item -> item.getId().equals(id)).findFirst();
            list.remove(first);

    }


}
