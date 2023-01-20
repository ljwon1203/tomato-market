package com.team8.shop.tomatomarket.dto;

import com.team8.shop.tomatomarket.entity.CustomerRequestForm;

import lombok.Getter;

@Getter
public class BuyReqDto {
    private final Long productId;
    private final Long userId;
    private final boolean isApproval;

    public BuyReqDto(CustomerRequestForm customerRequestForm){
        this.productId = customerRequestForm.getProduct().getId();
        this.userId = customerRequestForm.getUser().getId();
        this.isApproval = customerRequestForm.isApproval();
    }

}
