package com.team8.shop.tomatomarket.controller;

import com.team8.shop.tomatomarket.dto.*;
import com.team8.shop.tomatomarket.security.UserDetailsImpl;
import com.team8.shop.tomatomarket.service.SellerRequestFormService;
import com.team8.shop.tomatomarket.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final SellerRequestFormService sellerRequestFormService;


    @PostMapping("/auth/waitings")
    public void createSellerWaiting(@AuthenticationPrincipal UserDetails userDetails, @RequestBody SellerRequestDto dto){
        CreateDisapprovedSellerFormReqDto serviceRequestDto = new CreateDisapprovedSellerFormReqDto(userDetails.getUsername(), dto.getIntroduce());
        sellerRequestFormService.createDisapprovedForm(serviceRequestDto);
    }

    @PatchMapping("/users/{userId}")
    public UserResponseDto setProfile(@PathVariable Long userId, @RequestBody UserRequestDto userRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if(userDetails.getUser().getId().equals(userId)){
            UserMyProfileDto userMyProfileDto = new UserMyProfileDto(userDetails.getUsername(),userRequestDto.getNickname());
            return userService.update(userMyProfileDto);
        }
        else {throw new IllegalArgumentException();}
    }


    @GetMapping("/users/{userId}")
    public UserResponseDto getProfile(@AuthenticationPrincipal UserDetailsImpl userDetails){
        return userService.getProfile(userDetails.getUser());
    }
}
