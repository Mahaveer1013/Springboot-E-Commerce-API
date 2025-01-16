package com.E_Commerce.backend.service.product;

import com.E_Commerce.backend.dto.product.ProductRequest;
import com.E_Commerce.backend.dto.product.ProductResponse;
import com.E_Commerce.backend.model.PaginatedResponse;
import org.springframework.data.domain.Pageable;

public interface IProductService {

    ProductResponse getProduct(Long id);

    PaginatedResponse<ProductResponse> getAllProducts(Pageable pageable);

    ProductResponse addProduct(ProductRequest productRequest);

    ProductResponse updateProduct(Long id, ProductRequest productRequest);
    
    void deleteProduct(Long id);
}
