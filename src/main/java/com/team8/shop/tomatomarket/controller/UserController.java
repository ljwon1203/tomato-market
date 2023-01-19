package com.team8.shop.tomatomarket.controller;

import com.team8.shop.tomatomarket.dto.*;
import com.team8.shop.tomatomarket.security.UserDetailsImpl;
import com.team8.shop.tomatomarket.service.SellerRequestFormServiceImpl;
import com.team8.shop.tomatomarket.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userServiceImpl;
    private final SellerRequestFormServiceImpl sellerRequestFormServiceImpl;

    @PostMapping("/login")
    public void login(@RequestBody LoginReqDto reqDto, HttpServletResponse response){
        LoginRespDto loginRespDto = userServiceImpl.login(reqDto);
        response.addHeader("Authorization", loginRespDto.getJwtToken());
    }

    @PostMapping("/logout")
    public void logout(){}

    @PostMapping("/signup")
    public void signup(@RequestBody SignupReqDto reqDto){
        userServiceImpl.signup(reqDto);
    }

    @PostMapping("/users/auth/waitings")
    public void createSellerWaiting(@AuthenticationPrincipal UserDetails userDetails, @RequestBody SellerRequestDto dto){
        CreateDisapprovedSellerFormReqDto serviceRequestDto = new CreateDisapprovedSellerFormReqDto(userDetails.getUsername(), dto.getIntroduce());
        sellerRequestFormServiceImpl.createDisapprovedForm(serviceRequestDto);
    }

    //(고객)프로필 설정
    @PatchMapping("/users/{userId}")
    public UserResponseDto setProfile(@PathVariable Long userId, @RequestBody UserRequestDto userRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if(!userDetails.isValidId(userId)){
            throw new IllegalArgumentException("프로필 작성자와 일치하지 않습니다.");
        }

        UserMyProfileDto userMyProfileDto = new UserMyProfileDto(userDetails.getUserId(),userRequestDto.getNickname());
        return userServiceImpl.update(userMyProfileDto);
    }

    //(고객)프로필 조회
    @GetMapping("/users/{userId}")
    public UserResponseDto getProfile(@AuthenticationPrincipal UserDetailsImpl userDetails){
        Long userId = userDetails.getUserId();
        return userServiceImpl.getProfile(userId);
    }
}
