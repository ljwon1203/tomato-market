package com.team8.shop.tomatomarket.dto;

import com.team8.shop.tomatomarket.entity.Product;
import com.team8.shop.tomatomarket.entity.ProductCategory;
import com.team8.shop.tomatomarket.entity.User;
import lombok.Getter;

@Getter
public class ProductRequestDto {
    private String name;

    private int price;

    private String desc;

    private ProductCategory productCategory;

    private User user;

    public ProductRequestDto(Product product) {
        this.name = product.getName();
        this.price = product.getPrice();
        this.desc = product.getDesc();
        this.productCategory = product.getProductCategory();
        this.user = product.getSeller().getUser();
    }
}
