package com.team8.shop.tomatomarket.dto;


import lombok.Getter;

@Getter
public class UserBuyProductsReqDto {
    private final Long productId;
    private final Long userId;

    public UserBuyProductsReqDto(Long productId, Long userId){
        this.productId = productId;
        this.userId = userId;
    }
}
