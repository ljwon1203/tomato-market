package com.team8.shop.tomatomarket.service;

import com.team8.shop.tomatomarket.dto.*;

import java.util.List;

public interface SellerService {
    GetSellerRespDto getSellerBySellerId(Long sellerId);

    GetSellerRespDto getSellerByUserId(Long userId);

    List<ProductResponseDto> getMyProductList(Long userId);
    
    List<GetSellerRespDto>  getSellerList(PageableServiceReqDto dto);

    void disapproveSellerAuth(Long sellerId);
    
    GetSellerRespDto sellerUpdate(SellerServiceDto sellerServiceDto);

    void createProduct(CreateProductReqDto dto);

    void updateProduct(Long productId, ProductRequestDto productRequestDto);

    void deleteProduct(Long productId);

    List<QuotationResponseDto> getQuotation(GetQuotationReqDto dto);

    void approveQuotation(Long requestId);
}