package com.E_Commerce.backend.dto.product;

import com.E_Commerce.backend.model.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {

    public Product toEntity(ProductRequest productRequest) {
        Product product = new Product();
        product.setCategoryId(productRequest.getCategoryId());
        product.setDescription(productRequest.getDescription());
        product.setSKU(productRequest.getSKU());
        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());
        product.setDiscountId(productRequest.getDiscountId());
        return product;
    }

    public ProductResponse toResponse(Product product) {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setCategoryId(product.getCategoryId());
        productResponse.setDescription(product.getDescription());
        productResponse.setSKU(product.getSKU());
        productResponse.setName(product.getName());
        productResponse.setPrice(product.getPrice());
        productResponse.setDiscountId(product.getDiscountId());
        return productResponse;
    }


}
