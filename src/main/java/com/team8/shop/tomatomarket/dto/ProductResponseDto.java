package com.team8.shop.tomatomarket.dto;

import com.team8.shop.tomatomarket.entity.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductResponseDto {
    private Long id;

    private String name;

    private int price;

    private String description;

    private UserResponseDto username;

    private CategoryDto category;

    public ProductResponseDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.description = product.getDescription();
        this.username = new UserResponseDto(product.getSeller().getUser());
        this.category = new CategoryDto(product.getProductCategory());
    }
}

