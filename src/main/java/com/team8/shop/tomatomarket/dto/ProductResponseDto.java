package com.team8.shop.tomatomarket.dto;

import com.team8.shop.tomatomarket.entity.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductResponseDto {

    private String name;

    private int price;

    private String description;

    private String username;

    private CategoryDto category;

    public ProductResponseDto(Product product) {
        this.name = product.getName();
        this.price = product.getPrice();
        this.description = product.getDescription();
        this.username = product.getSeller().getUser().getUsername();
        this.category = new CategoryDto(product.getProductCategory());
    }
}

