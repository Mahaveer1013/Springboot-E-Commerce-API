package com.E_Commerce.backend.dto.category;

import com.E_Commerce.backend.model.Category;
import org.springframework.stereotype.Service;

@Service
public class CategoryMapper {
    public Category toEntity(CategoryRequest categoryRequest) {
        Category category = new Category();
        category.setName(categoryRequest.getName());
        category.setDescription(categoryRequest.getDescription());
        return category;
    }

    public CategoryResponse toResponse(Category category) {
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setId(category.getId());
        categoryResponse.setName(category.getName());
        categoryResponse.setDescription(category.getDescription());
        return categoryResponse;
    }
}
