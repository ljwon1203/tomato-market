package com.team8.shop.tomatomarket.dto;

import com.team8.shop.tomatomarket.entity.CustomerRequestForm;
import lombok.Getter;

@Getter
public class QuotationResponseDto {

    private final Long id;
    private final ProductResponseDto product;
    private final UserResponseDto user;
    private final boolean isApproval;

    public QuotationResponseDto(CustomerRequestForm customerRequestForm){
        this.id = customerRequestForm.getId();
        this.product = new ProductResponseDto(customerRequestForm.getProduct());
        this.user = new UserResponseDto(customerRequestForm.getUser());
        this.isApproval = customerRequestForm.isApproval();
    }
}