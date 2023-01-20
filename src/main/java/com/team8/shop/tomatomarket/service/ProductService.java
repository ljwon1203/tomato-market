package com.team8.shop.tomatomarket.service;

import com.team8.shop.tomatomarket.dto.ProductResponseDto;
import com.team8.shop.tomatomarket.entity.Product;
import com.team8.shop.tomatomarket.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;


    //#14 전체 판매상품 목록 조회
    @Transactional
    public List<ProductResponseDto> getProductList() {

        List<Product> products = productRepository.findAllByOrderByModifiedAtDesc();
        List<ProductResponseDto> productResponseDtoList = new ArrayList<>();

        for (Product product : products) {
            productResponseDtoList.add(new ProductResponseDto(product));
        }
        return productResponseDtoList;
    }


}
