package com.team8.shop.tomatomarket.dto;

import com.team8.shop.tomatomarket.entity.User;
import lombok.Getter;

@Getter
public class UserResponseDto {
    private final Long Id;
    private final String username;
    private final String nickname;

    public UserResponseDto(User user){
        this.Id = user.getId();
        this.username = user.getUsername();
        this.nickname = user.getNickname();
    }
}
