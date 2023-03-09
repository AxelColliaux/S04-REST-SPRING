package fr.wcs.springrest.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.wcs.springrest.entity.Book;
import fr.wcs.springrest.repository.BookRepository;

@RestController
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @GetMapping("/book")
    public List<Book> index(){
        return bookRepository.findAll();
    }

    @GetMapping("/book/{id}")
    public Book show(@PathVariable Integer id){
        return bookRepository.findById(id).orElse(null);
    }

    @PostMapping("/book/search")
    public List<Book> search(@RequestBody Map<String, String> body){
        String searchTerm = body.get("text");
        return bookRepository.findByTitleContainingOrDescriptionContaining(searchTerm, searchTerm);
    }

    @PostMapping("/book")
    public Book create(@RequestBody Map<String, String> body){
        String title = body.get("title");
        String description = body.get("description");
        String author = body.get("author");
        Book newBook = new Book(title, author, description);
        return bookRepository.save(newBook);
    }

    @PutMapping("/book/{id}")
    public Book update(@PathVariable Integer id, @RequestBody Map<String, String> body){
        // getting book
        Book book = bookRepository.findById(id).orElse(null);
        book.setTitle(body.get("title"));
        book.setDescription(body.get("description"));
        book.setAuthor(body.get("author"));
        return bookRepository.save(book);
    }

    @DeleteMapping("book/{id}")
    public boolean delete(@PathVariable Integer id){
        bookRepository.deleteById(id);
        return true;
    }
}
