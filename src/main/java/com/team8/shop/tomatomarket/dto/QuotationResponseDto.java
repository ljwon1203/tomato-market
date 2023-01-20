package com.team8.shop.tomatomarket.dto;

import com.team8.shop.tomatomarket.entity.CustomerRequestForm;
import lombok.Getter;

@Getter
public class QuotationResponseDto {
    private ProductResponseDto productResponseDto;
    private UserResponseDto userResponseDto;
    private boolean isApproval;

    public QuotationResponseDto(CustomerRequestForm customerRequestForm){
        this.productResponseDto = new ProductResponseDto(customerRequestForm.getProduct());
        this.userResponseDto = new UserResponseDto(customerRequestForm.getUser());
        this.isApproval = customerRequestForm.getIsApproval();
    }
}