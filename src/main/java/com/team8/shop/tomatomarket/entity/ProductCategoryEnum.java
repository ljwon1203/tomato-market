package com.team8.shop.tomatomarket.entity;

public enum ProductCategoryEnum {

    FURNITURE("가구"),
    BEAUTY("화장품"),
    SPORTS("스포츠"),
    BOOK("도서"),
    ELECTRONICS("가전제품");

    private final String name;

    ProductCategoryEnum(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
