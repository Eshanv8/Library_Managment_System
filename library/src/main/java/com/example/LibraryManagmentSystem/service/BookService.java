package com.example.LibraryManagmentSystem.service;

import com.example.LibraryManagmentSystem.model.Book;
import java.util.List;
import java.util.Optional;

public interface BookService {
    Book addBook(Book book);
    List<Book> getAllBooks();
    Optional<Book> getBookById(String id);
    Book updateBook(String id, Book book);
    void deleteBook(String id);
    
    // Custom requirements from Lab Sheet [cite: 126-128]
    List<Book> getBooksByPublicationYear(int year);
    String getGenreById(String id);
    void deleteBooksByPublicationYear(int year);
}