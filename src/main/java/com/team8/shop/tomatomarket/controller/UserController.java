package com.team8.shop.tomatomarket.controller;

import com.team8.shop.tomatomarket.dto.*;
import com.team8.shop.tomatomarket.service.SellerRequestFormService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final SellerRequestFormService sellerRequestFormService;

    @PostMapping("/auth/waitings")
    public void createSellerWaiting(@AuthenticationPrincipal UserDetails userDetails, @RequestBody SellerRequestDto dto){
        CreateDisapprovedSellerFormReqDto serviceRequestDto = new CreateDisapprovedSellerFormReqDto(userDetails.getUsername(), dto.getIntroduce());
        sellerRequestFormService.createDisapprovedForm(serviceRequestDto);
    }
}
