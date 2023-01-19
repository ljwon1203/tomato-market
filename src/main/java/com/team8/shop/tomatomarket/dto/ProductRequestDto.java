package com.team8.shop.tomatomarket.dto;

import com.team8.shop.tomatomarket.entity.Product;
import com.team8.shop.tomatomarket.entity.ProductCategory;
import com.team8.shop.tomatomarket.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductRequestDto {
    private String name;

    private int price;

    private String desc;

    private ProductCategory productCategory;

}
