package com.team8.shop.tomatomarket.service;

import com.team8.shop.tomatomarket.dto.GetSellerRespDto;
import com.team8.shop.tomatomarket.entity.Product;
import com.team8.shop.tomatomarket.entity.Seller;
import com.team8.shop.tomatomarket.entity.User;
import com.team8.shop.tomatomarket.repository.ProductRepository;
import com.team8.shop.tomatomarket.repository.SellerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SellerServiceImpl implements SellerService{
    private final SellerRepository sellerRepository;
    private final ProductRepository productRepository;

    //#17-2 판매자 정보 조회
    @Override
    public GetSellerRespDto getSeller(Long sellerId) {
        Seller seller = sellerRepository.findById(sellerId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 판매자 입니다.")
        );

        List<Product> products = productRepository.findAllById(seller.getId()).orElse(new ArrayList<>());

        return new GetSellerRespDto(seller, products);
    }

    @Override
    public List<GetSellerRespDto> getSellerList() {
        List<Seller> sellerList = sellerRepository.findAll();

        List<GetSellerRespDto> getSellerRespDtos = new ArrayList<>();
        for (Seller seller : sellerList) {
            List<Product> products = productRepository.findAllById(seller.getId()).orElse(new ArrayList<>());
            getSellerRespDtos.add(new GetSellerRespDto(seller, products));
        }
        return getSellerRespDtos;
    }

    //(판매자) 나의 판매상품 조회
    @Override
    public GetSellerRespDto getMyProductList(User user) {
        Seller seller = sellerRepository.findById(user.getId()).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 판매자 입니다."));

        List<Product>productList = productRepository.findAllByUserId(user.getId());
        return new GetSellerRespDto(seller, productList);
    }

    @Override
    public void disapproveSellerAuth(Long sellerId) {
        Seller seller = sellerRepository.findById(sellerId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 판매자 입니다.")
        );

        // 셀러를 제거해준다.
        seller.remove();

        // 유저의 권한을 고객으로 변경한다.
        seller.getUser().setRoleCustomer();

        // 변경사항을 저장합니다.
        sellerRepository.save(seller);
    }
}
