package com.team8.shop.tomatomarket.dto;

import lombok.Getter;

@Getter
public class GetQuotationReqDto extends PageableServiceReqDto{
    private final Long userId;
    public GetQuotationReqDto(Long userId, int page, int size) {
        super(page, size);
        this.userId = userId;
    }
}
