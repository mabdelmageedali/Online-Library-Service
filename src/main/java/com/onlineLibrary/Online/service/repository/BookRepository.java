package com.onlineLibrary.Online.service.repository;

import com.onlineLibrary.Online.service.entity.Author;
import com.onlineLibrary.Online.service.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    // Get books by author (corrected)
    List<Book> findByAuthorsContaining(Author author);

    // Optional: Search books by title or author name
    @Query("SELECT DISTINCT b FROM Book b LEFT JOIN b.authors a WHERE " +
            "LOWER(b.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(a.authorName) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Book> searchBooks(@Param("keyword") String keyword);
}
