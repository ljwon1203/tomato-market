package com.team8.shop.tomatomarket.repository;

import com.team8.shop.tomatomarket.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<List<Product>> findAllBySellerId(Long sellerId);
}
