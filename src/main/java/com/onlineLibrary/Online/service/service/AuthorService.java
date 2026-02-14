package com.onlineLibrary.Online.service.service;

import com.onlineLibrary.Online.service.entity.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    // Add new author
    Author addAuthor(Author author);

    // Get author by ID
    Optional<Author> getAuthorById(Integer id);

    // Get all authors
    List<Author> getAllAuthors();

    // Search authors by name
    List<Author> searchAuthors(String keyword);

    // Update author
    Author updateAuthor(Integer id, Author author);

    // Delete author
    void deleteAuthor(Integer id);
}
