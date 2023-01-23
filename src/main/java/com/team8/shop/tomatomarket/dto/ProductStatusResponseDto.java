package com.team8.shop.tomatomarket.dto;

import com.team8.shop.tomatomarket.entity.Product;
import lombok.Getter;

@Getter
public class ProductStatusResponseDto extends ProductResponseDto{
    private final boolean hasQuotation;
    public ProductStatusResponseDto(Product product, boolean hasQuotation) {
        super(product);
        this.hasQuotation = hasQuotation;
    }
}
