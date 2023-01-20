package com.team8.shop.tomatomarket.dto;

import com.team8.shop.tomatomarket.entity.User;
import com.team8.shop.tomatomarket.entity.UserRoleEnum;
import lombok.Getter;

@Getter
public class UserResponseDto {
    private final Long id;
    private final String username;
    private final String nickname;
    private final UserRoleEnum role;

    public UserResponseDto(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.nickname = user.getNickname();
        this.role = user.getRole();
    }
}
