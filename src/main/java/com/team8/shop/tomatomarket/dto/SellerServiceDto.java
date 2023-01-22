package com.team8.shop.tomatomarket.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SellerServiceDto {
    private final Long userId;
    private final String introduce;
}
