package com.team8.shop.tomatomarket.entity;

import lombok.*;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SellerRequestForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @OneToOne
    @JoinColumn(name="user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @Column(nullable = false)
    private String introduce;

    @Column(nullable = false)
    private boolean is_approval;

    public SellerRequestForm(User user, String introduce){
        this.user = user;
        this.introduce = introduce;
        this.is_approval = false;
    }

    public void approve(){
        this.is_approval = true;
    }
}
