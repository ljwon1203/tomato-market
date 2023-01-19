package com.team8.shop.tomatomarket.controller;

import com.team8.shop.tomatomarket.dto.*;
import com.team8.shop.tomatomarket.service.SellerRequestFormService;
import com.team8.shop.tomatomarket.service.SellerRequestFormServiceImpl;
import com.team8.shop.tomatomarket.service.UserService;
import com.team8.shop.tomatomarket.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userServiceImpl;
    private final SellerRequestFormServiceImpl sellerRequestFormServiceImpl;

    @PostMapping("/login")
    public void login(@RequestBody LoginReqDto reqDto, HttpServletResponse response){
        LoginRespDto loginRespDto = userServiceImpl.login(reqDto);
    }


    @PostMapping("/auth/waitings")
    public void createSellerWaiting(@AuthenticationPrincipal UserDetails userDetails, @RequestBody SellerRequestDto dto){
        CreateDisapprovedSellerFormReqDto serviceRequestDto = new CreateDisapprovedSellerFormReqDto(userDetails.getUsername(), dto.getIntroduce());
        sellerRequestFormServiceImpl.createDisapprovedForm(serviceRequestDto);
    }
}
