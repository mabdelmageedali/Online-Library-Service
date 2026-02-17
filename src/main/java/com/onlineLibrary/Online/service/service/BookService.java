package com.onlineLibrary.Online.service.service;

import com.onlineLibrary.Online.service.dto.book.*;

import java.util.List;

public interface BookService {

    BookResponseDTO createBook(BookRequestDTO dto);

    BookResponseDTO getBookById(Integer id);

    List<BookResponseDTO> getAllBooks();

    List<BookSummaryDTO> searchBooksByTitle(String keyword);

    List<BookSummaryDTO> getBooksByCategory(Integer categoryId);

    List<BookSummaryDTO> getBooksByAuthor(Integer authorId);

    BookResponseDTO updateBook(Integer id, BookUpdateDTO dto);

    void deleteBook(Integer id);

    FileDownloadResponse downloadBook(Integer id);
}