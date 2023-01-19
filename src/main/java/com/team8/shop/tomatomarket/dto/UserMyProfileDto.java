package com.team8.shop.tomatomarket.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserMyProfileDto {
    private  Long Id;
    private  String nickname;

    public UserMyProfileDto(Long userId, String nickname){
        this.Id = userId;
        this.nickname = nickname;
    }
}
