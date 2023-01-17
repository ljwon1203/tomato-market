package com.team8.shop.tomatomarket.controller;

import com.team8.shop.tomatomarket.dto.ProductResponseDto;
import com.team8.shop.tomatomarket.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequiredArgsConstructor
@RestController
public class ProductController {

    private final ProductService productService;

    //#14 전체 판매상품 목록 조회
    @GetMapping("/products")
    public ResponseEntity<List<ProductResponseDto>> getProductList() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProductList());

    }
}
