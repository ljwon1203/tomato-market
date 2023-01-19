package com.team8.shop.tomatomarket.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignupReqDto {
    private String username;
    private String nickname;
    private String password;
}
