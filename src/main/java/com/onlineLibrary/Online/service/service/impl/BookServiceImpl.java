package com.onlineLibrary.Online.service.service.impl;

import com.onlineLibrary.Online.service.entity.Book;
import com.onlineLibrary.Online.service.exception.BadRequestException;
import com.onlineLibrary.Online.service.exception.NotFoundException;
import com.onlineLibrary.Online.service.repository.BookRepository;
import com.onlineLibrary.Online.service.service.BookService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public Book addBook(Book book) {

        if (bookRepository.existsByTitle(book.getTitle())) {
            throw new BadRequestException("Book already exists");
        }

        return bookRepository.save(book);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Book> getBookById(Integer id) {
        return bookRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> searchBooksByTitle(String keyword) {
        return bookRepository.findByTitleContaining(keyword);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getBooksByCategory(Integer categoryId) {
        return bookRepository.findByCategoriesId(categoryId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getBooksByAuthor(Integer authorId) {
        return bookRepository.findByAuthorsId(authorId);
    }

    @Override
    public Book updateBook(Integer id, Book book) {

        Book oldBook = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Book not found"));

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
            throw new NotFoundException("Book not found");
        }

        bookRepository.deleteById(id);
    }
}