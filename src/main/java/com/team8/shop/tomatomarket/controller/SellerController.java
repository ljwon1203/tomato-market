package com.team8.shop.tomatomarket.controller;

import com.team8.shop.tomatomarket.dto.*;
import com.team8.shop.tomatomarket.service.SellerService;
import com.team8.shop.tomatomarket.security.UserDetailsImpl;
import com.team8.shop.tomatomarket.repository.SellerRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SellerController {
    private final SellerService sellerService;
    private final SellerRepository sellerRepository;

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
        _checkId(sellerId, userDetails);
        Long userId = userDetails.getUserId();
        // getMyProductList에서 user에 해당하는 productList를 반환해 준다
        return sellerService.getMyProductList(userId);
    }

    // #12 (판매자)판매 상품 등록
    @PostMapping("/sellers/{sellerId}/products")
    public void createProduct(@PathVariable Long sellerId,
                              @RequestBody ProductRequestDto productRequestDto,
                              @AuthenticationPrincipal UserDetailsImpl userDetails){
        _checkId(sellerId, userDetails);
        sellerService.createProduct(productRequestDto);
    }


    // #12 (판매자)판매 상품 수정
    @PatchMapping("/sellers/{sellerId}/products/{productId}")
    public void updateProduct(@PathVariable Long sellerId,
                              @PathVariable Long productId,
                              @RequestBody ProductRequestDto productRequestDto,
                              @AuthenticationPrincipal UserDetailsImpl userDetails){
        _checkId(sellerId, userDetails);
        sellerService.updateProduct(productId, productRequestDto);
    }


    // #12(판매자)판매 상품 삭제
    @DeleteMapping("/sellers/{sellerId}/products/{productId}")
    public void deleteProduct(@PathVariable Long sellerId,
                              @PathVariable Long productId,
                              @AuthenticationPrincipal UserDetailsImpl userDetails){
        _checkId(sellerId, userDetails);
        sellerService.deleteProduct(productId);
    }


    private void _checkId(Long sellerId, UserDetailsImpl userDetails){
        Long checkSellerUserId = sellerService.getSeller(sellerId).getUser().getId();
        if(!userDetails.isValidId(checkSellerUserId)){
            throw new IllegalArgumentException("등록된 정보와 일치하지 않습니다.");
        }
    }
}
