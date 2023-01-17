package com.team8.shop.tomatomarket.controller;

import com.team8.shop.tomatomarket.dto.GetSellerRespDto;
import com.team8.shop.tomatomarket.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sellers")
public class SellerController {
    private final SellerService sellerService;
    @GetMapping("/{sellerId}")
    public GetSellerRespDto getSeller(@PathVariable Long sellerId){
        return sellerService.getSeller(sellerId);
    }
}
