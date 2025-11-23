package com.example.LibraryManagmentSystem.repository;

import com.example.LibraryManagmentSystem.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface BookRepository extends MongoRepository<Book, String> {
    // Custom method to find books by year
    List<Book> findByPublicationYear(int publicationYear);
    
    // Custom method to delete by year
    void deleteByPublicationYear(int publicationYear);
}
