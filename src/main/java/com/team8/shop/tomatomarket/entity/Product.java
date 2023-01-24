package com.team8.shop.tomatomarket.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private int price;

    @Column
    private String description;

    @JoinColumn(name = "seller_id", nullable = false)
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Seller seller;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private ProductCategoryEnum productCategory;


    public Product(String name, int price, String description, Seller seller, ProductCategoryEnum productCategory){
        this.name = name;
        this.price = price;
        this.seller = seller;
        this.description = description;
        this.productCategory = productCategory;
    }

    public void updateProduct(String name, int price, String description, ProductCategoryEnum productCategory){
        this.name = name;
        this.price = price;
        this.description = description;
        this.productCategory = productCategory;
    }
}
