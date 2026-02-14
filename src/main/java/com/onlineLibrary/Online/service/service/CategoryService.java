package com.onlineLibrary.Online.service.service;

import com.onlineLibrary.Online.service.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    // Add new category
    Category addCategory(Category category);

    // Get category by ID
    Optional<Category> getCategoryById(Integer id);

    // Get all categories
    List<Category> getAllCategories();

    // Search categories by name
    List<Category> searchCategories(String keyword);

    // Update category
    Category updateCategory(Integer id, Category category);

    // Delete category
    void deleteCategory(Integer id);
}
