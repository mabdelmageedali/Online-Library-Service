package com.onlineLibrary.Online.service.service.impl;

import com.onlineLibrary.Online.service.dto.category.CategoryRequestDTO;
import com.onlineLibrary.Online.service.dto.category.CategoryResponseDTO;
import com.onlineLibrary.Online.service.dto.category.CategorySummaryDTO;
import com.onlineLibrary.Online.service.dto.category.CategoryUpdateDTO;
import com.onlineLibrary.Online.service.entity.Category;
import com.onlineLibrary.Online.service.exception.BadRequestException;
import com.onlineLibrary.Online.service.exception.NotFoundException;
import com.onlineLibrary.Online.service.mapper.CategoryMapper;
import com.onlineLibrary.Online.service.repository.CategoryRepository;
import com.onlineLibrary.Online.service.service.CategoryService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryResponseDTO createCategory(CategoryRequestDTO dto) {
        if (categoryRepository.existsByCategoryName(dto.getCategoryName())) {
            throw new BadRequestException("Category already exists with name: " + dto.getCategoryName());
        }

        Category category = categoryMapper.toEntity(dto);
        Category savedCategory = categoryRepository.save(category);
        return categoryMapper.toResponseDTO(savedCategory);
    }

    @Override
    @Transactional(readOnly = true)
    public CategoryResponseDTO getCategoryById(Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category not found with id: " + id));
        return categoryMapper.toResponseDTO(category);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategorySummaryDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categoryMapper.toSummaryDTOList(categories);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategorySummaryDTO> searchCategories(String keyword) {
        List<Category> categories = categoryRepository.findByCategoryNameContaining(keyword);
        return categoryMapper.toSummaryDTOList(categories);
    }

    @Override
    public CategoryResponseDTO updateCategory(Integer id, CategoryUpdateDTO dto) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category not found with id: " + id));

        categoryMapper.updateEntityFromDTO(dto, category);
        Category updatedCategory = categoryRepository.save(category);
        return categoryMapper.toResponseDTO(updatedCategory);
    }

    @Override
    public void deleteCategory(Integer id) {
        if (!categoryRepository.existsById(id)) {
            throw new NotFoundException("Category not found with id: " + id);
        }

        categoryRepository.deleteById(id);
    }
}