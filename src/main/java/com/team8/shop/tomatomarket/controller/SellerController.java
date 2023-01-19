package com.team8.shop.tomatomarket.controller;

import com.team8.shop.tomatomarket.dto.GetSellerRespDto;
import com.team8.shop.tomatomarket.dto.PageableServiceReqDto;
import com.team8.shop.tomatomarket.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SellerController {
    private final SellerService sellerService;

    //#17-1 판매자 목록 전체 조회
    @GetMapping("/sellers")
    public List<GetSellerRespDto> getSellerList(int page, int size){
        PageableServiceReqDto serviceReqDto = new PageableServiceReqDto(page, size);
        return sellerService.getSellerList(serviceReqDto);
    }

    //#17-2 판매자 정보 조회
    @GetMapping("/sellers/{sellerId}")
    public GetSellerRespDto getSeller(@PathVariable Long sellerId){
        return sellerService.getSeller(sellerId);
    }


}
