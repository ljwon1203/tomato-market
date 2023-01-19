package com.team8.shop.tomatomarket.service;

import com.team8.shop.tomatomarket.dto.GetSellerRespDto;
import com.team8.shop.tomatomarket.dto.ProductRequestDto;
import com.team8.shop.tomatomarket.entity.User;


public interface SellerService {
    GetSellerRespDto getSeller(Long sellerId);

    void disapproveSellerAuth(Long sellerId);

    void createProduct(ProductRequestDto productRequestDto);

    void updateProduct(Long productId, ProductRequestDto productRequestDto);

    void deleteProduct(Long productId);
}
