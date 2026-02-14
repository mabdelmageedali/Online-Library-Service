package com.onlineLibrary.Online.service.repository;

import com.onlineLibrary.Online.service.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    // Get category by exact name
    Optional<Category> findByCategoryName(String categoryName);

    // Check duplicate category
    Boolean existsByCategoryName(String categoryName);

    // Search categories
    List<Category> findByCategoryNameContaining(String keyword);

    // Get categories of a book
    List<Category> findByBooksId(Integer bookId);
}
