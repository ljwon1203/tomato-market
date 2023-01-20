package com.team8.shop.tomatomarket.repository;

import com.team8.shop.tomatomarket.entity.CustomerRequestForm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRequestFormRepository extends JpaRepository<CustomerRequestForm, Long> {
    Optional<CustomerRequestForm> findByUserIdAndProductId(Long userId, Long productId);
}
