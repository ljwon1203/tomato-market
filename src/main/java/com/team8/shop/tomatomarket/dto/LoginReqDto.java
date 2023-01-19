package com.team8.shop.tomatomarket.dto;

import lombok.*;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor
public class LoginReqDto {
    @NonNull
    private String username;
    @NonNull
    private String password;
}
