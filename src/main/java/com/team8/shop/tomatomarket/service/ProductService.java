package com.team8.shop.tomatomarket.service;

import com.team8.shop.tomatomarket.dto.ProductResponseDto;
import com.team8.shop.tomatomarket.dto.ProductStatusResponseDto;
import com.team8.shop.tomatomarket.dto.UserBuyProductsReqDto;
import com.team8.shop.tomatomarket.entity.CustomerRequestForm;
import com.team8.shop.tomatomarket.entity.Product;
import com.team8.shop.tomatomarket.entity.User;
import com.team8.shop.tomatomarket.repository.CustomerRequestFormRepository;
import com.team8.shop.tomatomarket.repository.ProductRepository;
import com.team8.shop.tomatomarket.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CustomerRequestFormRepository customerRequestFormRepository;

    //#14 전체 판매상품 목록 조회
    @Transactional
    public List<ProductResponseDto> getProductList() {
        List<Product> products = productRepository.findAll();
        List<ProductResponseDto> productResponseDtoList = new ArrayList<>();
        List<Long> requestIds = customerRequestFormRepository.findAll().stream().map(req -> req.getProduct().getId()).collect(Collectors.toList());
        System.out.println(requestIds);

        for (Product product : products) {
            productResponseDtoList.add(new ProductStatusResponseDto(product, requestIds.contains(product.getId())));
        }
        return productResponseDtoList;
    }

    //#15 판매자에게 구매요청
    public void postBuyRequest(UserBuyProductsReqDto userBuyProductsReqDto){
        CustomerRequestForm instance;
        Long userId = userBuyProductsReqDto.getUserId();
        Long productId = userBuyProductsReqDto.getProductId();

        Product product = productRepository.findById(productId).get();
        User user = userRepository.findById(userId).get();

        Optional<CustomerRequestForm> customerRequestForm = customerRequestFormRepository.findByUserIdAndProductId(userId,productId);

        //기존 상품에 대한 구매요청이 있다면, 재구매 요청
        if(customerRequestForm.isPresent()){
//            instance = customerRequestForm.get();
//            instance.disapprove();
            throw new IllegalArgumentException("이미 구매 요청한 상품입니다.");
        }
        else {
            //작업을 마친 뒤, isApproval = false여야한다.
            instance = new CustomerRequestForm(product, user);
            customerRequestFormRepository.save(instance);
        }
    }
}
