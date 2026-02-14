package com.onlineLibrary.Online.service.repository;

import com.onlineLibrary.Online.service.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    // Search by title (partial match)
    List<Book> findByTitleContaining(String keyword);

    // Filter by language
    List<Book> findByLanguage(String language);

    // Get books by category
    List<Book> findByCategoriesId(Integer categoryId);

    // Get books by author
    List<Book> findByAuthorsId(Integer authorId);

    // Check if book exists
    Boolean existsByTitle(String title);
}
