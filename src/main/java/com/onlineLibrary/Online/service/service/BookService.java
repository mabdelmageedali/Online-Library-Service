package com.onlineLibrary.Online.service.service;

import com.onlineLibrary.Online.service.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    // Add new book
    Book addBook(Book book);

    // Get book by ID
    Optional<Book> getBookById(Integer id);

    // Get all books
    List<Book> getAllBooks();

    // Search books by title
    List<Book> searchBooksByTitle(String keyword);

    // Get books by category
    List<Book> getBooksByCategory(Integer categoryId);

    // Get books by author
    List<Book> getBooksByAuthor(Integer authorId);

    // Update book data
    Book updateBook(Integer id, Book book);

    // Delete book
    void deleteBook(Integer id);
}
