package com.team8.shop.tomatomarket.service;

import com.team8.shop.tomatomarket.dto.GetSellerRespDto;

public interface SellerService {
    GetSellerRespDto getSeller(Long sellerId);
}
