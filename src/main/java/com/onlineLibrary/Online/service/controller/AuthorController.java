package com.onlineLibrary.Online.service.controller;

import com.onlineLibrary.Online.service.entity.Author;
import com.onlineLibrary.Online.service.exception.NotFoundException;
import com.onlineLibrary.Online.service.service.AuthorService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    // Add new author
    @PostMapping
    public ResponseEntity<Author> addAuthor(@Valid @RequestBody Author author) {

        Author savedAuthor = authorService.addAuthor(author);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedAuthor);
    }

    // Get author by id
    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthor(@PathVariable Integer id) {

        Author author = authorService.getAuthorById(id)
                .orElseThrow(() -> new NotFoundException("Author not found"));

        return ResponseEntity.ok(author);
    }

    // Get all authors
    @GetMapping
    public ResponseEntity<List<Author>> getAllAuthors() {

        return ResponseEntity.ok(authorService.getAllAuthors());
    }

    // Search authors by name
    @GetMapping("/search")
    public ResponseEntity<List<Author>> searchAuthors(
            @RequestParam String keyword
    ) {

        return ResponseEntity.ok(authorService.searchAuthors(keyword));
    }

    // Update author
    @PutMapping("/{id}")
    public ResponseEntity<Author> updateAuthor(
            @PathVariable Integer id,
            @Valid @RequestBody Author author
    ) {

        Author updatedAuthor = authorService.updateAuthor(id, author);

        return ResponseEntity.ok(updatedAuthor);
    }

    // Delete author
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Integer id) {

        authorService.deleteAuthor(id);

        return ResponseEntity.noContent().build();
    }
}
