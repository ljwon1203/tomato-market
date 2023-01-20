package com.team8.shop.tomatomarket.service;

import com.team8.shop.tomatomarket.dto.*;

import java.util.List;

public interface UserService {
    LoginRespDto login(LoginReqDto dto);
    
    void signup(SignupReqDto dto);

    UserResponseDto update(UserMyProfileDto userMyProfileDto);

    UserResponseDto getProfile(Long userId);

    List<UserResponseDto> getUserList(PageableServiceReqDto dto);
}
