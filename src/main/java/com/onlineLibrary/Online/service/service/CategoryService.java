package com.onlineLibrary.Online.service.service;

import com.onlineLibrary.Online.service.dto.category.CategoryRequestDTO;
import com.onlineLibrary.Online.service.dto.category.CategoryResponseDTO;
import com.onlineLibrary.Online.service.dto.category.CategorySummaryDTO;
import com.onlineLibrary.Online.service.dto.category.CategoryUpdateDTO;

import java.util.List;

public interface CategoryService {

    // Create new category
    CategoryResponseDTO createCategory(CategoryRequestDTO dto);

    // Get category by ID
    CategoryResponseDTO getCategoryById(Integer id);

    // Get all categories
    List<CategorySummaryDTO> getAllCategories();

    // Search categories by name
    List<CategorySummaryDTO> searchCategories(String keyword);

    // Update category
    CategoryResponseDTO updateCategory(Integer id, CategoryUpdateDTO dto);

    // Delete category
    void deleteCategory(Integer id);
}