package com.team8.shop.tomatomarket.dto;

import com.team8.shop.tomatomarket.entity.SellerRequestForm;
import lombok.Getter;

@Getter
public class UserBuyProductsReqDto {
    private final Long Id;
    private final Long productId;
    private final Long userId;
    private final boolean isApproval;

    public UserBuyProductsReqDto(Long requestId, Long productId, Long userId, boolean isApproval){
        this.Id = requestId;
        this.productId = productId;
        this.userId = userId;
        this.isApproval = isApproval;
    }
}
