package com.team8.shop.tomatomarket.entity;

import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
//    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @Column(nullable = false)
    private String introduce;

    @Column(nullable = false)
    private boolean is_removed;

    public Seller(User user, String introduce){
        this.user = user;
        this.introduce = introduce;
        this.is_removed = false;
    }

    public void updateIntroduce(String introduce){
        this.introduce = introduce;
    }

    public void remove(){
        this.is_removed = true;
    }
}
