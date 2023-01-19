package com.team8.shop.tomatomarket.service;

import com.team8.shop.tomatomarket.dto.LoginReqDto;
import com.team8.shop.tomatomarket.dto.LoginRespDto;

public interface UserService {
    LoginRespDto login(LoginReqDto dto);
}
