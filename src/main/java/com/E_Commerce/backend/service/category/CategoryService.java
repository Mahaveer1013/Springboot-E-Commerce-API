package com.E_Commerce.backend.service.category;

import com.E_Commerce.backend.dto.category.CategoryMapper;
import com.E_Commerce.backend.dto.category.CategoryRequest;
import com.E_Commerce.backend.dto.category.CategoryResponse;
import com.E_Commerce.backend.lib.exception.AlreadyExists;
import com.E_Commerce.backend.model.Category;
import com.E_Commerce.backend.model.PaginatedResponse;
import com.E_Commerce.backend.repository.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.ws.rs.NotFoundException;
import java.util.List;

@Service
public class CategoryService implements ICategoryService {

    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryMapper categoryMapper, CategoryRepository categoryRepository) {
        this.categoryMapper = categoryMapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryResponse addCategory(CategoryRequest categoryRequest) {
        validateCategoryExists(categoryRequest.getName());
        Category category = categoryMapper.toEntity(categoryRequest);
        Category newCategory = categoryRepository.save(category);
        return categoryMapper.toResponse(newCategory);
    }

    @Override
    public CategoryResponse updateCategory(Long id, CategoryRequest categoryRequest) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category not found"));

        if (categoryRequest.getName() != null && !categoryRequest.getName().equals(category.getName())) {
            validateCategoryExists(categoryRequest.getName());
        }

        if (categoryRequest.getName() != null) {
            category.setName(categoryRequest.getName());
        }

        if (categoryRequest.getDescription() != null) {
            category.setDescription(categoryRequest.getDescription());
        }

        return categoryMapper.toResponse(categoryRepository.save(category));
    }

    private void validateCategoryExists(String categoryName) {
        if (categoryRepository.existsByName(categoryName)) {
            throw new AlreadyExists(categoryName + " category already exists");
        }
    }

    @Override
    public PaginatedResponse<CategoryResponse> getAllCategories(Pageable pageable) {
        Page<Category> categoriesPage = categoryRepository.findAll(pageable);
        List<CategoryResponse> categoryResponses = categoriesPage.getContent()
                .stream()
                .map(categoryMapper::toResponse)
                .toList();

        return new PaginatedResponse<>(categoryResponses, categoriesPage.getNumber(),
                categoriesPage.getSize(), categoriesPage.getTotalElements(),
                categoriesPage.getTotalPages(), categoriesPage.isLast());
    }

    @Override
    public CategoryResponse getCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category not found"));
        return categoryMapper.toResponse(category);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.findById(id)
                .ifPresentOrElse(categoryRepository::delete,
                        () -> {
                            throw new NotFoundException("Category not found");
                        });
    }
}
