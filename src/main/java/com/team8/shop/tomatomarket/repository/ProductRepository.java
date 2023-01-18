package com.team8.shop.tomatomarket.repository;

import com.team8.shop.tomatomarket.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository {
    List<Product> findAllByOrderByModifiedAtDesc();
    List<Product> findAllById(Long id);
}
