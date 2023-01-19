package com.team8.shop.tomatomarket.controller;

import com.team8.shop.tomatomarket.dto.GetSellerRespDto;
import com.team8.shop.tomatomarket.dto.ProductRequestDto;
import com.team8.shop.tomatomarket.security.UserDetailsImpl;
import com.team8.shop.tomatomarket.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
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
        Long checkSellerUserId = sellerServiceImpl.getSeller(sellerId).getUser().getId();
        if(!userDetails.isValidId(checkSellerUserId)){
            throw new IllegalArgumentException("등록된 정보와 일치하지 않습니다.");
        }
    }
}
