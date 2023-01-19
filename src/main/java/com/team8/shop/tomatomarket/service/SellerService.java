package com.team8.shop.tomatomarket.service;

import com.team8.shop.tomatomarket.dto.GetSellerRespDto;
import com.team8.shop.tomatomarket.dto.PageableServiceReqDto;

import java.util.List;


public interface SellerService {
    GetSellerRespDto getSeller(Long sellerId);

    List<GetSellerRespDto>  getSellerList(PageableServiceReqDto dto);

    void disapproveSellerAuth(Long sellerId);
}
