package com.team8.shop.tomatomarket.entity;

public enum UserRoleEnum {
    ADMIN(Authority.ADMIN),
    SELLER(Authority.SELLER),
    CUSTOMER(Authority.CUSTOMER);

    private final String authority;

    UserRoleEnum(String authority){
        this.authority = authority;
    }

    public String getAuthority(){
        return this.authority;
    }

    public static class Authority{
        public static final String ADMIN = "ROLE_ADMIN";
        public static final String SELLER = "ROLE_SELLER";
        public static final String CUSTOMER = "ROLE_CUSTOMER";
    }
}
