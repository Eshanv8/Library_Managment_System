package com.example.LibraryManagmentSystem.controller;

import com.example.LibraryManagmentSystem.model.Book;
import com.example.LibraryManagmentSystem.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = "*")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable String id) {
        return bookService.getBookById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable String id, @RequestBody Book book) {
        if (!bookService.getBookById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bookService.updateBook(id, book));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable String id) {
        if (!bookService.getBookById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    // --- Endpoints for Custom Queries ---

    // Find by Year
    @GetMapping("/year/{year}")
    public List<Book> getBooksByYear(@PathVariable int year) {
        return bookService.getBooksByPublicationYear(year);
    }

    // Get Genre by ID
    @GetMapping("/{id}/genre")
    public String getGenreById(@PathVariable String id) {
        return bookService.getGenreById(id);
    }

    // Delete by Year
    @DeleteMapping("/year/{year}")
    public ResponseEntity<String> deleteBooksByYear(@PathVariable int year) {
        bookService.deleteBooksByPublicationYear(year);
        return ResponseEntity.ok("Books from year " + year + " deleted successfully");
    }
}