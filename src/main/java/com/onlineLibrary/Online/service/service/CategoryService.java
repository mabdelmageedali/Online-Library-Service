package com.onlineLibrary.Online.service.service;

import com.onlineLibrary.Online.service.dto.category.CategoryRequestDTO;
import com.onlineLibrary.Online.service.dto.category.CategoryResponseDTO;
import com.onlineLibrary.Online.service.dto.category.CategorySummaryDTO;
import com.onlineLibrary.Online.service.dto.category.CategoryUpdateDTO;

import java.util.List;

public interface CategoryService {

    CategoryResponseDTO createCategory(CategoryRequestDTO dto);

    CategoryResponseDTO getCategoryById(Integer id);

    List<CategorySummaryDTO> getAllCategories();

    List<CategorySummaryDTO> searchCategories(String keyword);

    CategoryResponseDTO updateCategory(Integer id, CategoryUpdateDTO dto);

    void deleteCategory(Integer id);
}