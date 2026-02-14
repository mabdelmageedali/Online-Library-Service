package com.onlineLibrary.Online.service.service.impl;

import com.onlineLibrary.Online.service.entity.Category;
import com.onlineLibrary.Online.service.repository.CategoryRepository;
import com.onlineLibrary.Online.service.service.CategoryService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category addCategory(Category category) {

        if (categoryRepository.existsByCategoryName(category.getCategoryName())) {
            throw new RuntimeException("Category already exists");
        }

        return categoryRepository.save(category);
    }

    @Override
    public Optional<Category> getCategoryById(Integer id) {

        return categoryRepository.findById(id);
    }

    @Override
    public List<Category> getAllCategories() {

        return categoryRepository.findAll();
    }

    @Override
    public List<Category> searchCategories(String keyword) {

        return categoryRepository.findByCategoryNameContaining(keyword);
    }

    @Override
    public Category updateCategory(Integer id, Category category) {

        Category oldCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        oldCategory.setCategoryName(category.getCategoryName());
        oldCategory.setCategoryDescription(category.getCategoryDescription());

        return categoryRepository.save(oldCategory);
    }

    @Override
    public void deleteCategory(Integer id) {

        if (!categoryRepository.existsById(id)) {
            throw new RuntimeException("Category not found");
        }

        categoryRepository.deleteById(id);
    }
}
