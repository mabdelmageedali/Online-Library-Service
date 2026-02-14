package com.onlineLibrary.Online.service.controller;

import com.onlineLibrary.Online.service.entity.Book;
import com.onlineLibrary.Online.service.exception.NotFoundException;
import com.onlineLibrary.Online.service.service.BookService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    // Add new book
    @PostMapping
    public ResponseEntity<Book> addBook(@Valid @RequestBody Book book) {

        Book savedBook = bookService.addBook(book);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
    }

    // Get book by id
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable Integer id) {

        Book book = bookService.getBookById(id)
                .orElseThrow(() -> new NotFoundException("Book not found"));

        return ResponseEntity.ok(book);
    }

    // Get all books
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {

        return ResponseEntity.ok(bookService.getAllBooks());
    }

    // Search by title
    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchBooks(
            @RequestParam String keyword
    ) {

        return ResponseEntity.ok(bookService.searchBooksByTitle(keyword));
    }

    // Get books by category
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Book>> getBooksByCategory(
            @PathVariable Integer categoryId
    ) {

        return ResponseEntity.ok(
                bookService.getBooksByCategory(categoryId)
        );
    }

    // Get books by author
    @GetMapping("/author/{authorId}")
    public ResponseEntity<List<Book>> getBooksByAuthor(
            @PathVariable Integer authorId
    ) {

        return ResponseEntity.ok(
                bookService.getBooksByAuthor(authorId)
        );
    }

    // Update book
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(
            @PathVariable Integer id,
            @Valid @RequestBody Book book
    ) {

        Book updatedBook = bookService.updateBook(id, book);

        return ResponseEntity.ok(updatedBook);
    }

    // Delete book
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Integer id) {

        bookService.deleteBook(id);

        return ResponseEntity.noContent().build();
    }
}
