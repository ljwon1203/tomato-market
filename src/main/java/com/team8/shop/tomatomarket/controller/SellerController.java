package com.team8.shop.tomatomarket.controller;

import com.team8.shop.tomatomarket.dto.GetSellerRespDto;
import com.team8.shop.tomatomarket.service.SellerService;
import com.team8.shop.tomatomarket.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sellers")
public class SellerController {
    private final SellerService sellerService;

    //#17-1 판매자 목록 전체 조회
    @GetMapping("")
    public List<GetSellerRespDto> getSellerList(){
        return sellerService.getSellerList();
    }

    //#17-2 판매자 정보 조회
    @GetMapping("/{sellerId}")
    public GetSellerRespDto getSeller(@PathVariable Long sellerId){
        return sellerService.getSeller(sellerId);
    }

    //#19 (판매자) 나의 판매상품 조회
    @GetMapping("/{sellerId}/products")
    public GetSellerRespDto getMyProductList(@AuthenticationPrincipal UserServiceImpl userServiceImpl) {
        return sellerService.getMyProductList(userServiceImpl.getUser());
    }

}
