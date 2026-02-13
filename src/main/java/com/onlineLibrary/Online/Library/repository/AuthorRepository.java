package com.onlineLibrary.Online.Library.repository;

import com.onlineLibrary.Online.Library.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

    // Get author by exact name
    Optional<Author> findByAuthorName(String authorName);

    // Check if author already exists
    Boolean existsByAuthorName(String authorName);

    // Get authors of a specific book
    List<Author> findByBooksId(Integer bookId);

    List<Author> findByAuthorNameContaining(String keyword);
}
