package com.team8.shop.tomatomarket.controller;

import com.team8.shop.tomatomarket.dto.*;
import com.team8.shop.tomatomarket.service.SellerService;
import com.team8.shop.tomatomarket.security.UserDetailsImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SellerController {
    private final SellerService sellerService;

    // 판매자 전체목록 조회
    @GetMapping("/sellers")
    public List<GetSellerRespDto> getSellerList(int page, int size){
        PageableServiceReqDto serviceReqDto = new PageableServiceReqDto(page, size);
        return sellerService.getSellerList(serviceReqDto);
    }

    // 판매자 정보 조회
    @GetMapping("/sellers/{sellerId}")
    public GetSellerRespDto getSeller(@PathVariable Long sellerId){
        return sellerService.getSeller(sellerId);
    }

    // (판매자)나의 판매상품 조회
    @GetMapping("/sellers/{sellerId}/products")
    public GetSellerRespDto getMyProductList(@PathVariable Long sellerId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        // 유저 아이디를 가져옵니다.
        Long checkSellerUserId = sellerService.getSeller(sellerId).getUser().getId();

        if (!userDetails.isValidId(checkSellerUserId)) {
            throw new IllegalArgumentException("등록된 정보와 일치하지 않습니다.");
        }

        // 일치한다면 로그인한 userId를 user에 담아준다.
        Long userId = userDetails.getUserId();
        // getMyProductList에서 user에 해당하는 productList를 반환해 준다
        return sellerService.getMyProductList(userId);
    }

    //(판매자) 프로필 설정
    @PatchMapping("/sellers/{sellerId}")
    public GetSellerRespDto setSellerProfile(@PathVariable Long sellerId, @RequestBody GetSellerReqDto getSellerReqDto , @AuthenticationPrincipal UserDetailsImpl userDetails){
        //판매자의 ID를 가져옵니다.
        if(!userDetails.isValidId(sellerId)){
            throw new IllegalArgumentException("프로필 작성자와 일치하지 않습니다.");
        }
        return sellerService.sellerUpdate(sellerId,getSellerReqDto);
    }


}
