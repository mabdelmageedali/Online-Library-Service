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

    List<Book> findByTitleContaining(String keyword);

    List<Book> findByLanguage(String language);

    List<Book> findByCategoriesId(Integer categoryId);

    List<Book> findByAuthorsId(Integer authorId);

    Boolean existsByTitle(String title);

    List<Book> findByAuthorsContaining(Author author);

    @Query("SELECT DISTINCT b FROM Book b LEFT JOIN b.authors a WHERE " +
            "LOWER(b.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(a.authorName) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Book> searchBooks(@Param("keyword") String keyword);
}
