package com.team8.shop.tomatomarket.controller;

import com.team8.shop.tomatomarket.dto.*;
import com.team8.shop.tomatomarket.entity.UserRoleEnum;
import com.team8.shop.tomatomarket.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final SellerServiceImpl sellerServiceImpl;
    private final SellerRequestFormServiceImpl sellerRequestFormServiceImpl;
    private final UserServiceImpl userServiceImpl;

    @GetMapping("/users")
    @Secured(UserRoleEnum.Authority.ADMIN)
    public List<UserResponseDto> getUserList(int page, int size){
        PageableServiceReqDto serviceReqDto = new PageableServiceReqDto(page, size);
        return userServiceImpl.getUserList(serviceReqDto);
    }

    @GetMapping("/sellers")
    @Secured(UserRoleEnum.Authority.ADMIN)
    public List<GetSellerRespDto> getSellers(int page, int size){
        PageableServiceReqDto serviceReqDto = new PageableServiceReqDto(page, size);
        return sellerServiceImpl.getSellerList(serviceReqDto);
    }

    @PatchMapping("/sellers/{sellerId}")
    @Secured(UserRoleEnum.Authority.ADMIN)
    public void disapproveSellerAuth(@PathVariable Long sellerId){
        sellerServiceImpl.disapproveSellerAuth(sellerId);
    }

    @GetMapping("/auth/waitings")
    @Secured(UserRoleEnum.Authority.ADMIN)
    public List<GetSellerWaitingsRespDto> getSellerWaitings(){
        return sellerRequestFormServiceImpl.getSellerWaitings();
    }

    @PatchMapping("/auth/waiting/{waitingId}")
    @Secured(UserRoleEnum.Authority.ADMIN)
    public void approveSellerAuth(@PathVariable Long waitingId){
        sellerRequestFormServiceImpl.approveSellerAuth(waitingId);
    }
}
