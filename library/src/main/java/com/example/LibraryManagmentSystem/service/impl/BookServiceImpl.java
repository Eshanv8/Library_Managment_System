package com.example.LibraryManagmentSystem.service.impl;
import com.example.LibraryManagmentSystem.model.Book;
import com.example.LibraryManagmentSystem.repository.BookRepository;
import com.example.LibraryManagmentSystem.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> getBookById(String id) {
        return bookRepository.findById(id);
    }

    @Override
    public Book updateBook(String id, Book book) {
        book.setId(id); // Ensure the ID matches
        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(String id) {
        bookRepository.deleteById(id);
    }

    // --- Custom Queries Implementation ---

    @Override
    public List<Book> getBooksByPublicationYear(int year) {
        return bookRepository.findByPublicationYear(year);
    }

    @Override
    public String getGenreById(String id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.map(Book::getGenre).orElse("Book not found");
    }

    @Override
    public void deleteBooksByPublicationYear(int year) {
        bookRepository.deleteByPublicationYear(year);
    }
}