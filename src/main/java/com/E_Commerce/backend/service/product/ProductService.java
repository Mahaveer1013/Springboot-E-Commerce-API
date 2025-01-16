package com.E_Commerce.backend.service.product;

import com.E_Commerce.backend.dto.product.ProductMapper;
import com.E_Commerce.backend.dto.product.ProductRequest;
import com.E_Commerce.backend.dto.product.ProductResponse;
import com.E_Commerce.backend.model.PaginatedResponse;
import com.E_Commerce.backend.model.Product;
import com.E_Commerce.backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.ws.rs.NotFoundException;
import java.util.List;

@Service
public class ProductService implements IProductService {

    private final ProductMapper productMapper;
    private final ProductRepository productRepository;

    // Constructor Injection for better testability and immutability
    @Autowired
    public ProductService(ProductMapper productMapper, ProductRepository productRepository) {
        this.productMapper = productMapper;
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponse getProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found"));
        return productMapper.toResponse(product);
    }

    @Override
    public PaginatedResponse<ProductResponse> getAllProducts(Pageable pageable) {
        Page<Product> productsPage = productRepository.findAll(pageable);
        List<ProductResponse> productResponses = productsPage.getContent().stream()
                .map(productMapper::toResponse)
                .toList();

        return new PaginatedResponse<>(productResponses, productsPage.getNumber(), productsPage.getSize(),
                productsPage.getTotalElements(), productsPage.getTotalPages(), productsPage.isLast());
    }

    @Override
    public ProductResponse addProduct(@Valid ProductRequest productRequest) {
        Product product = productMapper.toEntity(productRequest);
        Product newProduct = productRepository.save(product);
        return productMapper.toResponse(newProduct);
    }

    @Override
    public ProductResponse updateProduct(Long id, @Valid ProductRequest productRequest) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found"));

        updateProductFields(product, productRequest);
        return productMapper.toResponse(productRepository.save(product));
    }

    // Refactored update logic into a separate helper method to reduce redundancy
    private void updateProductFields(Product product, ProductRequest productRequest) {
        if (productRequest.getDescription() != null) product.setDescription(productRequest.getDescription());
        if (productRequest.getSKU() != null) product.setSKU(productRequest.getSKU());
        if (productRequest.getPrice() != null) product.setPrice(productRequest.getPrice());
        if (productRequest.getDiscountId() != null) product.setDiscountId(productRequest.getDiscountId());
        if (productRequest.getName() != null) product.setName(productRequest.getName());
        if (productRequest.getStock() != null) product.setStock(productRequest.getStock());
        if (productRequest.getCategoryId() != null) product.setCategoryId(productRequest.getCategoryId());
    }

    @Override
    public void deleteProduct(Long id) {
        // Use a method to check and delete the product
        deleteProductIfExists(id);
    }

    // Encapsulated deletion logic in a helper method for cleaner code
    private void deleteProductIfExists(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found"));
        productRepository.delete(product);
    }
}
