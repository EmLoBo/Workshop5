package pl.coderslab.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.coderslab.model.Book;
import pl.coderslab.model.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RestController
@RequestMapping(value = "/books")
public class BookController {

    //    private MockBookService mockBookService;
//    public BookController(MockBookService mockBookService) {
//        this.mockBookService = mockBookService;
//    }
    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    public Book book;


    @RequestMapping("/helloBook")
    public Book helloBook() {
        return new Book(1L, "9788324631766", "Thinking in Java",
                "Bruce Eckel", "Helion", "programming");
    }


    @RequestMapping(value = "/addBook", method = RequestMethod.POST, produces = "text/html; charset=utf-8")
    @ResponseBody
    public String addBook(@RequestParam Book book) {
        bookService.addBook(book);

        return "redirect:list";
    }

    @RequestMapping({"/list", "/books"})
    @ResponseBody
    public List<Book> getList() {

        return bookService.getBooks();

    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable Long id) {
        return this.bookService.get(id).orElseThrow(() -> {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity not found"
            );
        });
    }


    @RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
    @ResponseBody
    public void deleteBook(@PathVariable Long id) {

        bookService.deleteBook(id);

        //return "redirect:list";

    }



    @PutMapping(value="/edit")
    @ResponseBody
    public void editBook(@PathVariable Long id){
        bookService.editBook(id);
    }



}
