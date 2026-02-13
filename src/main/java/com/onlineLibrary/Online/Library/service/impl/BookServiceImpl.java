package com.onlineLibrary.Online.Library.service.impl;

import com.onlineLibrary.Online.Library.entity.Book;
import com.onlineLibrary.Online.Library.repository.BookRepository;
import com.onlineLibrary.Online.Library.service.BookService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public Book addBook(Book book) {

        if (bookRepository.existsByTitle(book.getTitle())) {
            throw new RuntimeException("Book already exists");
        }

        return bookRepository.save(book);
    }

    @Override
    public Optional<Book> getBookById(Integer id) {

        return bookRepository.findById(id);
    }

    @Override
    public List<Book> getAllBooks() {

        return bookRepository.findAll();
    }

    @Override
    public List<Book> searchBooksByTitle(String keyword) {

        return bookRepository.findByTitleContaining(keyword);
    }

    @Override
    public List<Book> getBooksByCategory(Integer categoryId) {

        return bookRepository.findByCategoriesId(categoryId);
    }

    @Override
    public List<Book> getBooksByAuthor(Integer authorId) {

        return bookRepository.findByAuthorsId(authorId);
    }

    @Override
    public Book updateBook(Integer id, Book book) {

        Book oldBook = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        oldBook.setTitle(book.getTitle());
        oldBook.setDescription(book.getDescription());
        oldBook.setLanguage(book.getLanguage());
        oldBook.setPagesCount(book.getPagesCount());
        oldBook.setPublishDate(book.getPublishDate());

        return bookRepository.save(oldBook);
    }

    @Override
    public void deleteBook(Integer id) {

        if (!bookRepository.existsById(id)) {
            throw new RuntimeException("Book not found");
        }

        bookRepository.deleteById(id);
    }
}
