package com.onlineLibrary.Online.service.service;

import com.onlineLibrary.Online.service.dto.book.*;

import java.util.List;

public interface BookService {

    // Create new book with file
    BookResponseDTO createBook(BookRequestDTO dto);

    // Get book by ID
    BookResponseDTO getBookById(Integer id);

    // Get all books
    List<BookResponseDTO> getAllBooks();

    // Search books by title
    List<BookSummaryDTO> searchBooksByTitle(String keyword);

    // Get books by category
    List<BookSummaryDTO> getBooksByCategory(Integer categoryId);

    // Get books by author
    List<BookSummaryDTO> getBooksByAuthor(Integer authorId);

    // Update book
    BookResponseDTO updateBook(Integer id, BookUpdateDTO dto);

    // Delete book
    void deleteBook(Integer id);

    // Download book file
    FileDownloadResponse downloadBook(Integer id);
}