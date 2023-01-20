package com.team8.shop.tomatomarket.dto;

import com.team8.shop.tomatomarket.entity.User;
import lombok.Getter;

@Getter
public class UserNickDto {
    private final String nickname;

    public UserNickDto(User user){
        this.nickname = user.getNickname();
    }
}
