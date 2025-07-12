package com.coffe.platform_api.repository;

import com.coffe.platform_api.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}