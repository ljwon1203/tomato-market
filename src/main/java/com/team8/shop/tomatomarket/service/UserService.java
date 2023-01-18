package com.team8.shop.tomatomarket.service;

import com.team8.shop.tomatomarket.dto.UserMyProfileDto;
import com.team8.shop.tomatomarket.dto.UserRequestDto;
import com.team8.shop.tomatomarket.dto.UserResponseDto;
import com.team8.shop.tomatomarket.entity.User;
import org.springframework.transaction.annotation.Transactional;

public interface UserService {
    UserResponseDto update(UserMyProfileDto userMyProfileDto);

    UserResponseDto getProfile(User user);

}
