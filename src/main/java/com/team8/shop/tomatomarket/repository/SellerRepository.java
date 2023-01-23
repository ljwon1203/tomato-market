package com.team8.shop.tomatomarket.repository;

import com.team8.shop.tomatomarket.entity.Seller;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SellerRepository extends JpaRepository<Seller, Long> {
    Optional<Seller> findByUserId(Long userId);

    Page<Seller> findAllByIsRemovedFalse(Pageable pageable);
}
