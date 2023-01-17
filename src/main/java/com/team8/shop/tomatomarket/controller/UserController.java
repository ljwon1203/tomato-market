package com.team8.shop.tomatomarket.controller;

import com.team8.shop.tomatomarket.dto.CreateDisapprovedSellerFormReqDto;
import com.team8.shop.tomatomarket.dto.SellerRequestDto;
import com.team8.shop.tomatomarket.security.UserDetailsImpl;
import com.team8.shop.tomatomarket.service.SellerRequestFormService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final SellerRequestFormService sellerRequestFormService;

    @PostMapping("/auth/waitings")
    public void createSellerWaiting(@AuthenticationPrincipal UserDetails userDetails, @RequestBody SellerRequestDto dto){
        CreateDisapprovedSellerFormReqDto serviceRequestDto = new CreateDisapprovedSellerFormReqDto(userDetails.getUsername(), dto.getIntroduce())
        sellerRequestFormService.createDisapprovedForm(serviceRequestDto);
    }
}
