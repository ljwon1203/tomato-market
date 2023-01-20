package com.team8.shop.tomatomarket.service;

import com.team8.shop.tomatomarket.dto.ProductResponseDto;
import com.team8.shop.tomatomarket.dto.UserBuyProductsReqDto;
import com.team8.shop.tomatomarket.entity.CustomerRequestForm;
import com.team8.shop.tomatomarket.entity.Product;
import com.team8.shop.tomatomarket.entity.User;
import com.team8.shop.tomatomarket.repository.CustomerRequestFormRepository;
import com.team8.shop.tomatomarket.repository.ProductRepository;
import com.team8.shop.tomatomarket.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    private final CustomerRequestFormRepository customerRequestFormRepository;

    public ProductService(ProductRepository productRepository, UserRepository userRepository, CustomerRequestFormRepository customerRequestFormRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.customerRequestFormRepository = customerRequestFormRepository;
    }


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

    //#15 판매자에게 구매요청
    public void postBuyRequest(UserBuyProductsReqDto userBuyProductsReqDto){
        CustomerRequestForm instance;
        Long userId = userBuyProductsReqDto.getUserId();
        Long productId = userBuyProductsReqDto.getProductId();

        Product product = productRepository.findById(productId).get();
        User user = userRepository.findById(userId).get();

        Optional<CustomerRequestForm> customerRequestForm = customerRequestFormRepository.findByUserId(userId);

        if(customerRequestForm.isPresent()){
            instance = customerRequestForm.get();
            instance.isApproval();
        }
        else{
            instance = new CustomerRequestForm(product,user);
        }
        customerRequestFormRepository.save(instance);
    }

}
