package com.team8.shop.tomatomarket.dto;

import com.team8.shop.tomatomarket.entity.Product;
import com.team8.shop.tomatomarket.entity.ProductCategory;
import com.team8.shop.tomatomarket.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductResponseDto {

    private String name;

    private int price;

    private String  desc;

    private User user ;

    private ProductCategory productCategory;

    public ProductResponseDto(Product product) {
        this.name = product.getName();
        this.price = product.getPrice();
        this.desc = product.getDesc();
        this.user = product.getUser();
        this.productCategory = product.getProductCategory();
    }
}

