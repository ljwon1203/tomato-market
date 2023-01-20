package com.team8.shop.tomatomarket.controller;

import com.team8.shop.tomatomarket.dto.BuyReqDto;
import com.team8.shop.tomatomarket.dto.ProductResponseDto;
import com.team8.shop.tomatomarket.dto.UserBuyProductsReqDto;
import com.team8.shop.tomatomarket.repository.UserRepository;
import com.team8.shop.tomatomarket.security.UserDetailsImpl;
import com.team8.shop.tomatomarket.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
public class ProductController {

    private final ProductService productService;

    //#14 전체 판매상품 목록 조회
    @GetMapping("/products")
    public List<ProductResponseDto> getProductList() {
        return productService.getProductList();
    }

    //#15 (고객) 상품 구매 요청
    @PostMapping("/products/{productId}/quotations")
    public void BuyRequest(@PathVariable Long Id, @RequestBody BuyReqDto buyReqDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        UserBuyProductsReqDto userBuyProductsReqDto = new UserBuyProductsReqDto(Id, buyReqDto.getProductId(), userDetails.getUserId(), buyReqDto.isApproval());
        productService.postBuyRequest(userBuyProductsReqDto);
    }
}
