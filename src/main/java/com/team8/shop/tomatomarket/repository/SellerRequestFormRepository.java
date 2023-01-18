package com.team8.shop.tomatomarket.repository;

import com.team8.shop.tomatomarket.entity.SellerRequestForm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SellerRequestFormRepository extends JpaRepository<SellerRequestForm, Long> {
    boolean existsByUserId(Long user_id);
    Optional<SellerRequestForm> findByUserId(Long user_id);
}
