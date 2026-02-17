package com.onlineLibrary.Online.service.controller;

import com.onlineLibrary.Online.service.dto.category.CategoryRequestDTO;
import com.onlineLibrary.Online.service.dto.category.CategoryResponseDTO;
import com.onlineLibrary.Online.service.dto.category.CategorySummaryDTO;
import com.onlineLibrary.Online.service.dto.category.CategoryUpdateDTO;
import com.onlineLibrary.Online.service.service.CategoryService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    // Create new category
    @PostMapping
    public ResponseEntity<CategoryResponseDTO> createCategory(@Valid @RequestBody CategoryRequestDTO dto) {
        CategoryResponseDTO response = categoryService.createCategory(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Get category by id
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> getCategory(@PathVariable Integer id) {
        CategoryResponseDTO response = categoryService.getCategoryById(id);
        return ResponseEntity.ok(response);
    }

    // Get all categories
    @GetMapping
    public ResponseEntity<List<CategorySummaryDTO>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    // Search categories by name
    @GetMapping("/search")
    public ResponseEntity<List<CategorySummaryDTO>> searchCategories(@RequestParam String keyword) {
        return ResponseEntity.ok(categoryService.searchCategories(keyword));
    }

    // Update category
    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> updateCategory(
            @PathVariable Integer id,
            @Valid @RequestBody CategoryUpdateDTO dto) {
        CategoryResponseDTO response = categoryService.updateCategory(id, dto);
        return ResponseEntity.ok(response);
    }

    // Delete category
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Integer id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}