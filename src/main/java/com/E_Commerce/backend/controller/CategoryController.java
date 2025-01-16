package com.E_Commerce.backend.controller;


import com.E_Commerce.backend.dto.category.CategoryRequest;
import com.E_Commerce.backend.dto.category.CategoryResponse;
import com.E_Commerce.backend.lib.roleAnnotation.Admin;
import com.E_Commerce.backend.model.PaginatedResponse;
import com.E_Commerce.backend.response.ApiResponse;
import com.E_Commerce.backend.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllCategory(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        PaginatedResponse<CategoryResponse> categories = categoryService.getAllCategories(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse(null, categories)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getCategory(@PathVariable Long id) {
        CategoryResponse categoryResponse = categoryService.getCategory(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse(null, categoryResponse)
        );
    }

    @PostMapping
    @Admin
    public ResponseEntity<ApiResponse> addCategory(@RequestBody CategoryRequest categoryRequest) {
        CategoryResponse categoryResponse = categoryService.addCategory(categoryRequest);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse("Category added successfully", categoryResponse)
        );
    }

    @PutMapping("/{id}")
    @Admin
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable Long id, @RequestBody CategoryRequest categoryRequest) {
        CategoryResponse categoryResponse = categoryService.updateCategory(id, categoryRequest);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse("Category updated successfully", categoryResponse)
        );
    }

    @DeleteMapping("/{id")
    @Admin
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse("Category deleted successfully", null)
        );
    }
}
