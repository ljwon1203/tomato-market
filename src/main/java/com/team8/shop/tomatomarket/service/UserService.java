package com.team8.shop.tomatomarket.service;

import com.team8.shop.tomatomarket.dto.UserMyProfileDto;
import com.team8.shop.tomatomarket.dto.UserResponseDto;
import com.team8.shop.tomatomarket.entity.User;


public interface UserService {
    UserResponseDto update(UserMyProfileDto userMyProfileDto);

    UserResponseDto getProfile(User user);

}
