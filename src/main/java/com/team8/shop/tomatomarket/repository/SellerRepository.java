package com.team8.shop.tomatomarket.repository;

import com.team8.shop.tomatomarket.entity.Seller;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SellerRepository extends JpaRepository<Seller, Long> {
}
