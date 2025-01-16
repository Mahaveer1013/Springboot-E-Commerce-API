package com.E_Commerce.backend.repository;

import com.E_Commerce.backend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
