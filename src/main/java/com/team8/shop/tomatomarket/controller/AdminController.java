package com.team8.shop.tomatomarket.controller;

import com.team8.shop.tomatomarket.dto.GetSellerRespDto;
import com.team8.shop.tomatomarket.entity.UserRoleEnum;
import com.team8.shop.tomatomarket.service.SellerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final SellerServiceImpl sellerService;

    @GetMapping("/sellers")
    @Secured(UserRoleEnum.Authority.ADMIN)
    public List<GetSellerRespDto> getSellers(){
        return sellerService.getSellerList();
    }
}
