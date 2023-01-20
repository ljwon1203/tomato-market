package com.team8.shop.tomatomarket.dto;

import com.team8.shop.tomatomarket.entity.CustomerRequestForm;
import lombok.Getter;

@Getter
public class QuatationResponseDto {
    private Long productId;
    private Long userId;
    private boolean isApproval;

    public QuatationResponseDto(CustomerRequestForm customerRequestForm){
        this.productId = customerRequestForm.getProduct().getId();
        this.userId = customerRequestForm.getUser().getId();
        this.isApproval = customerRequestForm.getIsApproval();
    }
}
