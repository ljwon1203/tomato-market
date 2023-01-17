package com.team8.shop.tomatomarket.dto;

import com.team8.shop.tomatomarket.entity.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductResponseDto {

    private String name;

    private int price;

    private String  desc;

    private String seller_nickname;

    private String category_name;

    public ProductResponseDto(Product product) {
        this.name = product.getName();
        this.price = product.getPrice();
        this.desc = product.getDesc();
        this.seller_nickname = product.getSeller_nickname();
        this.category_name = product.getCategory_name();
    }
}

