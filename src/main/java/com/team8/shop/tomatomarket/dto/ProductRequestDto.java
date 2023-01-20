package com.team8.shop.tomatomarket.dto;

import com.team8.shop.tomatomarket.entity.ProductCategoryEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProductRequestDto {
    private String name;

    private int price;

    private String description;

    private ProductCategoryEnum productCategory;

}
