package com.team8.shop.tomatomarket.dto;

import com.team8.shop.tomatomarket.entity.*;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class GetSellerRespDto {
    private final Long id;
    private final String nickname;
    private final String introduce;
    private final List<String> categories;

    public GetSellerRespDto(Seller seller, List<Product> products){
        this.id = seller.getId();
        this.nickname = seller.getUser().getNickname();
        this.introduce = seller.getIntroduce();
        this.categories = products.stream().map(product -> product.getProductCategory().getName)
                                            .distinct()
                                            .collect(Collectors.toList());
    }
}
