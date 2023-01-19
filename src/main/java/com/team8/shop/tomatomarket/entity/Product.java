package com.team8.shop.tomatomarket.entity;

import com.team8.shop.tomatomarket.dto.ProductRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private int price;

    @Column
    private String desc;

    @Column
    private String nickname;

    @JoinColumn(name = "seller_id", nullable = false)
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Seller seller;

    @OneToOne
    @JoinColumn(name = "category_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ProductCategory productCategory;


    public Product(ProductRequestDto productRequestDto){
        this.name = productRequestDto.getName();
        this.price = productRequestDto.getPrice();
        this.desc = productRequestDto.getDesc();
        this.productCategory = productRequestDto.getProductCategory();
    }

    public void updateProduct(ProductRequestDto productRequestDto){
        this.name = productRequestDto.getName();
        this.price = productRequestDto.getPrice();
        this.desc = productRequestDto.getDesc();
        this.productCategory = productRequestDto.getProductCategory();
    }

}
