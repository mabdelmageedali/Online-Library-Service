package com.onlineLibrary.Online.service.controller;

import com.onlineLibrary.Online.service.dto.book.*;
import com.onlineLibrary.Online.service.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;


    @GetMapping
    public ResponseEntity<List<BookResponseDTO>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDTO> getBook(@PathVariable Integer id) {
        BookResponseDTO response = bookService.getBookById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<List<BookSummaryDTO>> searchBooks(@RequestParam String keyword) {
        return ResponseEntity.ok(bookService.searchBooksByTitle(keyword));
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<BookSummaryDTO>> getBooksByCategory(@PathVariable Integer categoryId) {
        return ResponseEntity.ok(bookService.getBooksByCategory(categoryId));
    }

    @GetMapping("/author/{authorId}")
    public ResponseEntity<List<BookSummaryDTO>> getBooksByAuthor(@PathVariable Integer authorId) {
        return ResponseEntity.ok(bookService.getBooksByAuthor(authorId));
    }


    @GetMapping("/{id}/download")
    @PreAuthorize("hasAnyRole('USER', 'AUTHOR', 'ADMIN')")
    public ResponseEntity<Resource> downloadBook(@PathVariable Integer id) {
        FileDownloadResponse response = bookService.downloadBook(id);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(response.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + response.getFilename() + "\"")
                .body(response.getResource());
    }

    @GetMapping("/{id}/read")
    @PreAuthorize("hasAnyRole('USER', 'AUTHOR', 'ADMIN')")
    public ResponseEntity<Resource> readBook(@PathVariable Integer id) {
        FileDownloadResponse response = bookService.downloadBook(id);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(response.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "inline; filename=\"" + response.getFilename() + "\"")
                .body(response.getResource());
    }



    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('AUTHOR')")
    public ResponseEntity<BookResponseDTO> createBook(@Valid @ModelAttribute BookRequestDTO dto) {
        BookResponseDTO response = bookService.createBook(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAnyRole('AUTHOR', 'ADMIN')")
    public ResponseEntity<BookResponseDTO> updateBook(
            @PathVariable Integer id,
            @Valid @ModelAttribute BookUpdateDTO dto) {
        BookResponseDTO response = bookService.updateBook(id, dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('AUTHOR', 'ADMIN')")
    public ResponseEntity<Void> deleteBook(@PathVariable Integer id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }


//    @DeleteMapping("/{id}/force")
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<Void> forceDeleteBook(@PathVariable Integer id) {
//        bookService.deleteBook(id);
//        return ResponseEntity.noContent().build();
//    }
}