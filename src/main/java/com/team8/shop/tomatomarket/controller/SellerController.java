package com.team8.shop.tomatomarket.controller;

import com.team8.shop.tomatomarket.dto.*;
import com.team8.shop.tomatomarket.security.UserDetailsImpl;
import com.team8.shop.tomatomarket.service.SellerServiceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SellerController {
    private final SellerServiceImpl sellerServiceImpl;

    // 판매자 전체목록 조회
    @GetMapping("/sellers")
    public List<GetSellerRespDto> getSellerList(@RequestParam Integer page, @RequestParam Integer size){
        PageableServiceReqDto serviceReqDto = new PageableServiceReqDto(page, size);
        return sellerServiceImpl.getSellerList(serviceReqDto);
    }

    // 판매자 정보 조회
    @GetMapping("/sellers/{userId}")
    public GetSellerRespDto getSeller(@PathVariable Long userId){
        return sellerServiceImpl.getSeller(userId);
    }

    // (판매자)나의 판매상품 조회
    @GetMapping("/sellers/{sellerId}/products")
    public GetSellerRespDto getMyProductList(@PathVariable Long sellerId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        _checkId(sellerId, userDetails);
        Long userId = userDetails.getUserId();
        // getMyProductList에서 user에 해당하는 productList를 반환해 준다
        return sellerServiceImpl.getMyProductList(userId);
    }

    // #12 (판매자)판매 상품 등록
    @PostMapping("/sellers/{sellerId}/products")
    public void createProduct(@PathVariable Long sellerId,
                              @RequestBody ProductRequestDto productRequestDto,
                              @AuthenticationPrincipal UserDetailsImpl userDetails){
        _checkId(sellerId, userDetails);
        CreateProductReqDto dto = new CreateProductReqDto(sellerId, productRequestDto.getName(), productRequestDto.getPrice(), productRequestDto.getDescription(), productRequestDto.getProductCategory());
        sellerServiceImpl.createProduct(dto);
    }

    // #12 (판매자)판매 상품 수정
    @PatchMapping("/sellers/{sellerId}/products/{productId}")
    public void updateProduct(@PathVariable Long sellerId,
                              @PathVariable Long productId,
                              @RequestBody ProductRequestDto productRequestDto,
                              @AuthenticationPrincipal UserDetailsImpl userDetails){
        _checkId(sellerId, userDetails);
        sellerServiceImpl.updateProduct(productId, productRequestDto);
    }

    // #12(판매자)판매 상품 삭제
    @DeleteMapping("/sellers/{sellerId}/products/{productId}")
    public void deleteProduct(@PathVariable Long sellerId,
                              @PathVariable Long productId,
                              @AuthenticationPrincipal UserDetailsImpl userDetails){
        _checkId(sellerId, userDetails);
        sellerServiceImpl.deleteProduct(productId);
    }


    // #18 (판매자) 고객 요청 목록 조회
    @GetMapping("/sellers/{sellerId}/quotations")
    public List<QuotationResponseDto> getQuotation(@PathVariable Long sellerId,
                                                   @AuthenticationPrincipal UserDetailsImpl userDetails,
                                                   int page, int size){
        _checkId(sellerId, userDetails);
        PageableServiceReqDto serviceReqDto = new PageableServiceReqDto(page, size);
        return sellerServiceImpl.getQuotation(serviceReqDto);
    }

    // #18 (판매자) 고객 구매 요청 승인
    @PatchMapping("/sellers/{sellerId}/quotations/{requestId}")
    public void approveQuotation(@PathVariable Long sellerId,
                                 @PathVariable Long requestId,
                                 @AuthenticationPrincipal UserDetailsImpl userDetails){
        _checkId(sellerId, userDetails);
        sellerServiceImpl.approveQuotation(requestId);
    }

    //(판매자) 프로필 설정
    @PatchMapping("/sellers/{sellerId}")
    public GetSellerRespDto setSellerProfile(@PathVariable Long sellerId, @RequestBody GetSellerReqDto getSellerReqDto , @AuthenticationPrincipal UserDetailsImpl userDetails){
        Long checkSellerUserId = sellerServiceImpl.getSeller(sellerId).getUser().getId();
        if(!userDetails.isValidId(checkSellerUserId)){
            throw new IllegalArgumentException("프로필 작성자와 일치하지 않습니다.");
        }
        SellerServiceDto sellerServiceDto = new SellerServiceDto(userDetails.getUserId(), getSellerReqDto.getIntroduce());
        return sellerServiceImpl.sellerUpdate(sellerServiceDto);
    }

    // 내부 함수 : {sellerId}와 로그인한 유저가 같은 사람인지 검증
    private void _checkId(Long sellerId, UserDetailsImpl userDetails){
        Long checkSellerUserId = sellerServiceImpl.getSeller(sellerId).getUser().getId();
        if(!userDetails.isValidId(checkSellerUserId)){
            throw new IllegalArgumentException("등록된 정보와 일치하지 않습니다.");
        }
    }
}
