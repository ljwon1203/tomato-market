package com.team8.shop.tomatomarket.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRoleEnum role;

    public User(String email, String nickname, String password, UserRoleEnum role){
        this.username = email;
        this.nickname = nickname;
        this.password = password;
        this.role = role;
    }

    public User(String email, String nickname, String password){
        this.username = email;
        this.nickname = nickname;
        this.password = password;
        // 기본값을 고객으로 셋팅합니다.
        this.role = UserRoleEnum.CUSTOMER;
    }

    // 패스워드 검증용
    public boolean isValidPassword(String password){
        return this.password.equals(password);
    }

    //닉네임 업데이트
    public void updateNickName(String nickname){
        this.nickname = nickname;
    }

    // Seller -> Customer 로 역할을 변경 시 사용합니다.
    public void setRoleCustomer(){
        this.role = UserRoleEnum.CUSTOMER;
    }

    // Customer -> Seller 역할 변경 시 사용합니다.
    public void setRoleSeller(){
        this.role = UserRoleEnum.SELLER;
    }


}
