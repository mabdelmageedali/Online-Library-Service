package com.onlineLibrary.Online.service.controller;

import com.onlineLibrary.Online.service.entity.Category;
import com.onlineLibrary.Online.service.exception.NotFoundException;
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

    // Add new category
    @PostMapping
    public ResponseEntity<Category> addCategory(@Valid @RequestBody Category category) {

        Category savedCategory = categoryService.addCategory(category);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
    }

    // Get category by id
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable Integer id) {

        Category category = categoryService.getCategoryById(id)
                .orElseThrow(() -> new NotFoundException("Category not found"));

        return ResponseEntity.ok(category);
    }

    // Get all categories
    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {

        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    // Search categories by name
    @GetMapping("/search")
    public ResponseEntity<List<Category>> searchCategories(
            @RequestParam String keyword
    ) {

        return ResponseEntity.ok(categoryService.searchCategories(keyword));
    }

    // Update category
    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(
            @PathVariable Integer id,
            @Valid @RequestBody Category category
    ) {

        Category updatedCategory = categoryService.updateCategory(id, category);

        return ResponseEntity.ok(updatedCategory);
    }

    // Delete category
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Integer id) {

        categoryService.deleteCategory(id);

        return ResponseEntity.noContent().build();
    }
}
