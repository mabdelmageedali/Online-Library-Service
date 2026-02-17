package com.onlineLibrary.Online.service.repository;

import com.onlineLibrary.Online.service.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Optional<Category> findByCategoryName(String categoryName);

    Boolean existsByCategoryName(String categoryName);

    List<Category> findByCategoryNameContaining(String keyword);

    List<Category> findByBooksId(Integer bookId);
}
