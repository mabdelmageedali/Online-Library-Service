package com.onlineLibrary.Online.service.controller;

import com.onlineLibrary.Online.service.dto.author.AuthorRequestDTO;
import com.onlineLibrary.Online.service.dto.author.AuthorResponseDTO;
import com.onlineLibrary.Online.service.dto.author.AuthorSummaryDTO;
import com.onlineLibrary.Online.service.dto.author.AuthorUpdateDTO;
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

    // Create new author
    @PostMapping
    public ResponseEntity<AuthorResponseDTO> createAuthor(@Valid @RequestBody AuthorRequestDTO dto) {
        AuthorResponseDTO response = authorService.createAuthor(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Get author by id
    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponseDTO> getAuthor(@PathVariable Integer id) {
        AuthorResponseDTO response = authorService.getAuthorById(id);
        return ResponseEntity.ok(response);
    }

    // Get all authors
    @GetMapping
    public ResponseEntity<List<AuthorResponseDTO>> getAllAuthors() {
        return ResponseEntity.ok(authorService.getAllAuthors());
    }

    // Search authors by name
    @GetMapping("/search")
    public ResponseEntity<List<AuthorSummaryDTO>> searchAuthors(@RequestParam String keyword) {
        return ResponseEntity.ok(authorService.searchAuthors(keyword));
    }

    // Update author
    @PutMapping("/{id}")
    public ResponseEntity<AuthorResponseDTO> updateAuthor(
            @PathVariable Integer id,
            @Valid @RequestBody AuthorUpdateDTO dto) {
        AuthorResponseDTO response = authorService.updateAuthor(id, dto);
        return ResponseEntity.ok(response);
    }

    // Delete author
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Integer id) {
        authorService.deleteAuthor(id);
        return ResponseEntity.noContent().build();
    }
}