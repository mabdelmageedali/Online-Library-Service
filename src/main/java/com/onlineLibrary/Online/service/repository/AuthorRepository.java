package com.onlineLibrary.Online.service.repository;

import com.onlineLibrary.Online.service.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

    Optional<Author> findByAuthorName(String authorName);

    Boolean existsByAuthorName(String authorName);

    List<Author> findByBooksId(Integer bookId);

    List<Author> findByAuthorNameContaining(String keyword);
}
