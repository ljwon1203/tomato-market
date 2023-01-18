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
    private final SellerServiceImpl sellerService;
    private final SellerRequestFormServiceImpl sellerRequestFormService;

    @GetMapping("/sellers")
    @Secured(UserRoleEnum.Authority.ADMIN)
    public List<GetSellerRespDto> getSellers(){
        return sellerService.getSellerList();
    }

    @GetMapping("/auth/waitings")
    @Secured(UserRoleEnum.Authority.ADMIN)
    public List<GetSellerWaitingsRespDto> getSellerWaitings(){
        return sellerRequestFormService.getSellerWaitings();
    }
}
