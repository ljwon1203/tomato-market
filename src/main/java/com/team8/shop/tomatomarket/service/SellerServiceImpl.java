package com.team8.shop.tomatomarket.service;

import com.team8.shop.tomatomarket.dto.GetSellerRespDto;
import com.team8.shop.tomatomarket.entity.Product;
import com.team8.shop.tomatomarket.entity.Seller;
import com.team8.shop.tomatomarket.entity.User;
import com.team8.shop.tomatomarket.repository.ProductRepository;
import com.team8.shop.tomatomarket.repository.SellerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    //#17-1 판매자 목록 전체 조회
    @Override
    public List<GetSellerRespDto> getSellerList() {
        List<Seller> sellerList = sellerRepository.findAll();
        // sellerPepository에 있는걸 가져와서 sellerList에 넣음 List

        List<GetSellerRespDto> getSellerRespDtos = new ArrayList<>();
        // getsellerRespDtos ArrayList로 만든다.
        for (Seller seller : sellerList) {
            List<Product> products = productRepository.findAllById(seller.getId()).orElse(new ArrayList<>());
            getSellerRespDtos.add(new GetSellerRespDto(seller, products));
            //sellerList에 있는 seller를 하나씩 꺼내서
            //seller와 product을 getSellerRespDtos에 담아준다.
        }
        return getSellerRespDtos;
            //반환해준다

    }

    //#19 (판매자) 나의 판매상품 조회
    public GetSellerRespDto getMyProductList(User user) {
        Seller seller = sellerRepository.findById(user.getId()).orElseThrow(
                () -> new IllegalArgumentException("권한이 없습니다.")
        );
        // userId를 사용해서 sellerRepository에 등록되어 있는 값을 가져와 seller에 담아준다.
        // seller가 아닐 경우 IllegalArgumentException 발생

        List<Product> products = productRepository.findAllBySellerId(seller.getId()).orElse(new ArrayList<>());
        // sellerId를 사용해서 productRepository에 등록되어있는 product를 products에 담아준다.
        return new GetSellerRespDto(seller, products);
    }
}
