package com.team8.shop.tomatomarket.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LoginRespDto {
    @NonNull
    private final String jwtToken;
}
