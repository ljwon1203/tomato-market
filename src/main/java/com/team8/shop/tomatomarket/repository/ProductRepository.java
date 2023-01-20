package com.team8.shop.tomatomarket.repository;

import com.team8.shop.tomatomarket.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByOrderByModifiedAtDesc();
    List<Product> findAllById(Long id);
    List<Product> findAllByUserId(Long userid);
    List<Product> findAllBySellerId(Long sellerId);
}
