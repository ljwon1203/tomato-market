package com.team8.shop.tomatomarket.dto;

import com.team8.shop.tomatomarket.entity.Product;
import com.team8.shop.tomatomarket.entity.ProductCategory;
import com.team8.shop.tomatomarket.entity.ProductCategoryEnum;
import com.team8.shop.tomatomarket.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ProductResponseDto {

    private String name;

    private int price;

    private String desc;

    private String username;

    private CategoryDto category;

    public ProductResponseDto(Product product) {
        this.name = product.getName();
        this.price = product.getPrice();
        this.desc = product.getDesc();
        this.username = product.getSeller().getUser().getUsername();
        this.category = new CategoryDto(product.getProductCategory());
    }
}

