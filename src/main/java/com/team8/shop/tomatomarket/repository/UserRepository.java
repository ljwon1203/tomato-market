package com.team8.shop.tomatomarket.repository;

import com.team8.shop.tomatomarket.entity.User;
import com.team8.shop.tomatomarket.entity.UserRoleEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    Page<User> findAllByRole(UserRoleEnum role, Pageable pageable);
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByNickname(String nickname);
}
