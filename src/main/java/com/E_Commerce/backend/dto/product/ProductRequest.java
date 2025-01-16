package com.E_Commerce.backend.dto.product;

import com.E_Commerce.backend.model.Category;
import lombok.Data;

@Data
public class ProductRequest {
    private String name;

    private String description;

    private String SKU;

    private Category categoryId;

    private String stock;

    private String price;

    private String discountId;

}
