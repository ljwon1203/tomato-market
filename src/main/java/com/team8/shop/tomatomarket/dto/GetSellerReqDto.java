package com.team8.shop.tomatomarket.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class GetSellerReqDto {
    private final String nickname;
    private final String introduce;
    private final List<String> categories;
}
