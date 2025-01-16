package com.E_Commerce.backend.service.category;

import com.E_Commerce.backend.dto.category.CategoryRequest;
import com.E_Commerce.backend.dto.category.CategoryResponse;
import com.E_Commerce.backend.model.PaginatedResponse;
import org.springframework.data.domain.Pageable;

public interface ICategoryService {

    CategoryResponse addCategory(CategoryRequest categoryRequest);

    CategoryResponse updateCategory(Long id, CategoryRequest categoryRequest);

    PaginatedResponse<CategoryResponse> getAllCategories(Pageable pageable);

    CategoryResponse getCategory(Long id);

    void deleteCategory(Long id);
}
