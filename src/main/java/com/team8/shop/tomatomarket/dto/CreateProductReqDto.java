package com.team8.shop.tomatomarket.dto;

import com.team8.shop.tomatomarket.entity.ProductCategoryEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateProductReqDto {
    private final Long sellerId;
    private final String name;
    private final int price;
    private final String description;
    private final ProductCategoryEnum productCategory;
}
