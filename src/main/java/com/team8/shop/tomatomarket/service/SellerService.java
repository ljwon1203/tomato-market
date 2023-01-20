package com.team8.shop.tomatomarket.service;

import com.team8.shop.tomatomarket.dto.*;

import java.util.List;


public interface SellerService {
    GetSellerRespDto getSeller(Long sellerId);

    GetSellerRespDto getMyProductList(Long userId);
    
    List<GetSellerRespDto>  getSellerList(PageableServiceReqDto dto);

    void disapproveSellerAuth(Long sellerId);
}
