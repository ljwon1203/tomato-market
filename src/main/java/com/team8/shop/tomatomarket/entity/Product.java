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




    public Product(String name, int price, String desc, ProductCategory productCategory){
        this.name = name;
        this.price = price;
        this.desc = desc;
        this.productCategory = productCategory;

    }

    public void updateProduct(String name, int price, String desc, ProductCategory productCategory){
        this.name = name;
        this.price = price;
        this.desc = desc;
        this.productCategory = productCategory;
    }

}
