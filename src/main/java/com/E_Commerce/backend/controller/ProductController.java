package com.E_Commerce.backend.controller;

import com.E_Commerce.backend.dto.product.ProductRequest;
import com.E_Commerce.backend.dto.product.ProductResponse;
import com.E_Commerce.backend.lib.roleAnnotation.Admin;
import com.E_Commerce.backend.lib.roleAnnotation.Seller;
import com.E_Commerce.backend.model.PaginatedResponse;
import com.E_Commerce.backend.response.ApiResponse;
import com.E_Commerce.backend.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllProduct(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        PaginatedResponse<ProductResponse> products = productService.getAllProducts(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse(null, products)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getProduct(@PathVariable Long id) {
        ProductResponse productResponse = productService.getProduct(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse(null, productResponse)
        );
    }

    @PostMapping
    @Seller
    public ResponseEntity<ApiResponse> addProduct(@RequestBody ProductRequest productRequest) {
        ProductResponse productResponse = productService.addProduct(productRequest);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse("Product added successfully", productResponse)
        );
    }

    @PutMapping("/{id}")
    @Seller
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable Long id, @RequestBody ProductRequest productRequest) {
        ProductResponse productResponse = productService.updateProduct(id, productRequest);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse("Product updated successfully", productResponse)
        );
    }

    @DeleteMapping("/{id")
    @Admin
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse("Product deleted successfully", null)
        );
    }
}
