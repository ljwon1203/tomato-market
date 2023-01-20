package com.team8.shop.tomatomarket.dto;

import com.team8.shop.tomatomarket.entity.ProductCategoryEnum;
import lombok.Getter;

@Getter
public class CategoryDto {

    private ProductCategoryEnum id;
    private String name;

    public CategoryDto(ProductCategoryEnum categoryEnum){
        this.id = categoryEnum;
        this.name = categoryEnum.getName();
    }

}
