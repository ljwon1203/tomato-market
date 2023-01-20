package com.team8.shop.tomatomarket.service;

import com.team8.shop.tomatomarket.dto.PageableServiceReqDto;
import com.team8.shop.tomatomarket.dto.UserResponseDto;

import java.util.List;

public interface AdminService {

    List<UserResponseDto> getUserList(PageableServiceReqDto dto);
}
