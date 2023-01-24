package com.team8.shop.tomatomarket.dto;

import com.team8.shop.tomatomarket.entity.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class ProductResponseDto {
    private final Long id;

    private final String name;

    private final int price;

    private final String description;

    private final UserResponseDto user;

    private final CategoryDto category;

    public ProductResponseDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.description = product.getDescription();
        this.user = new UserResponseDto(product.getSeller().getUser());
        this.category = new CategoryDto(product.getProductCategory());
    }
}

