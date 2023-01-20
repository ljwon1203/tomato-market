package com.team8.shop.tomatomarket.service;

import com.team8.shop.tomatomarket.dto.*;
import com.team8.shop.tomatomarket.entity.Product;
import com.team8.shop.tomatomarket.entity.Seller;
import com.team8.shop.tomatomarket.entity.User;
import com.team8.shop.tomatomarket.repository.ProductRepository;
import com.team8.shop.tomatomarket.repository.SellerRepository;
import com.team8.shop.tomatomarket.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SellerServiceImpl implements SellerService {
    private final SellerRepository sellerRepository;
    private final ProductRepository productRepository;

    //#17-2 판매자 정보 조회
    @Override
    public GetSellerRespDto getSeller(Long sellerId) {
        Seller seller = sellerRepository.findById(sellerId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 판매자 입니다.")
        );

        List<Product> products = productRepository.findAllById(seller.getId());

        return new GetSellerRespDto(seller, products);
    }

    @Override
    public List<GetSellerRespDto> getSellerList(PageableServiceReqDto dto) {
        int page = dto.getPage();
        int size = dto.getSize();
        String sortBy = dto.getSortBy();
        boolean isAsc = dto.isAsc();

        Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);

        // sellerPepository에 있는걸 가져와서 sellerList에 넣음 List
        List<Seller> sellerList = sellerRepository.findAll(pageable).toList();

        // getsellerRespDtos ArrayList로 만든다.
        List<GetSellerRespDto> getSellerRespDtos = new ArrayList<>();

        for (Seller seller : sellerList) {
            //sellerList에 있는 seller를 하나씩 꺼내서
            List<Product> products = productRepository.findAllById(seller.getId());
            //seller와 product을 getSellerRespDtos에 담아준다.
            getSellerRespDtos.add(new GetSellerRespDto(seller, products));
        }
        return getSellerRespDtos;
    }

    //(판매자) 나의 판매상품 조회
    @Override
    public GetSellerRespDto getMyProductList(Long userId) {
        Seller seller = sellerRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 판매자 입니다."));

        List<Product> productList = productRepository.findAllByUserId(userId);
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

    //(판매자) 프로필 설정
    @Override
    public GetSellerRespDto sellerUpdate(SellerServiceDto sellerServiceDto) {
        String introduce = sellerServiceDto.getIntroduce();
        List<Product> products = productRepository.findAllById(sellerServiceDto.getSellerId());

        Seller seller = _getSeller(sellerServiceDto.getSellerId());

        seller.updateIntroduce(introduce);
        sellerRepository.save(seller);
        return new GetSellerRespDto(seller, products);

    }

    // 내부 사용: 판매자 검증 by id
    private Seller _getSeller(Long sellerId) {
        return sellerRepository.findById(sellerId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 판매자 입니다.")
        );

    // #12 (판매자) 판매 상품 등록
    @Override
    public void createProduct(ProductRequestDto productRequestDto){
        Product product = new Product(productRequestDto.getName(),
                                      productRequestDto.getPrice(),
                                      productRequestDto.getDesc(),
                                      productRequestDto.getProductCategory());
        productRepository.save(product);
    }

    // #12 (판매자) 판매 상품 수정
    @Override
    public void updateProduct(Long productId, ProductRequestDto productRequestDto){
        Product product = _getProduct(productId);
        product.updateProduct(productRequestDto.getName(),
                              productRequestDto.getPrice(),
                              productRequestDto.getDesc(),
                              productRequestDto.getProductCategory());
        productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long productId){
        Product product = _getProduct(productId);
        productRepository.delete(product);
    }

    private Product _getProduct(Long productId){
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new IllegalArgumentException("조회하신 상품이 존재하지 않습니다.")
        );
        return product;
    }
}
