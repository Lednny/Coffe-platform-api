package com.coffe.platform_api.repository;

import com.coffe.platform_api.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(UUID userId);
}