package com.team8.shop.tomatomarket.dto;

import com.team8.shop.tomatomarket.entity.CustomerRequestForm;
import lombok.Getter;

@Getter
public class QuotationResponseDto {
    private ProductResponseDto product;
    private UserResponseDto user;
    private boolean isApproval;

    public QuotationResponseDto(CustomerRequestForm customerRequestForm){
        this.product = new ProductResponseDto(customerRequestForm.getProduct());
        this.user = new UserResponseDto(customerRequestForm.getUser());
        this.isApproval = customerRequestForm.getIsApproval();
    }
}