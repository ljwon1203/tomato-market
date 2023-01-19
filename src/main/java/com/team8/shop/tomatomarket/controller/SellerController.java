package com.team8.shop.tomatomarket.controller;

import com.team8.shop.tomatomarket.dto.GetSellerRespDto;
import com.team8.shop.tomatomarket.entity.User;
import com.team8.shop.tomatomarket.repository.SellerRepository;
import com.team8.shop.tomatomarket.security.UserDetailsImpl;
import com.team8.shop.tomatomarket.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sellers")
public class SellerController {
    private final SellerService sellerService;
    private final SellerRepository sellerRepository;

    //판매자 목록 전체 조회
    @GetMapping("")
    public List<GetSellerRespDto> getSellerList() {
        return sellerService.getSellerList();
    }

    //판매자 정보 조회
    @GetMapping("/{sellerId}")
    public GetSellerRespDto getSeller(@PathVariable Long sellerId) {
        return sellerService.getSeller(sellerId);
    }

    //(판매자)나의 판매상품 조회
    @GetMapping("/{sellerId}/products")
    public GetSellerRespDto getMyProductList(@PathVariable Long sellerId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long checkSellerId = sellerService.getSeller(sellerId).getId();
        Long checkUserId = userDetails.getUserId();

        if (checkSellerId.equals(checkUserId)) {
            User user = userDetails.getUser();
            // 일치한다면 로그인한 userId를 user에 담아준다.
            return sellerService.getMyProductList(user);
            // getMyProductList에서 user에 해당하는 productList를 반환해 준다
        } else {
            throw new IllegalArgumentException("등록된 정보와 일치하지 않습니다.");
        }
    }
}
