package com.team8.shop.tomatomarket.dto;

import lombok.*;

@Getter
@RequiredArgsConstructor
public class CreateDisapprovedSellerFormReqDto {
    @NonNull
    private final String introduce;

    @NonNull
    private final String username;
}
