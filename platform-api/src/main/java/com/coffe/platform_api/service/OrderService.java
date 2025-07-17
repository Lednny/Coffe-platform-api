package com.coffe.platform_api.service;

import com.coffe.platform_api.dto.order.OrderDTO;
import java.util.List;
import java.util.UUID;

public interface OrderService {
    OrderDTO createOrder(UUID userId);
    List<OrderDTO> getOrdersForUser(UUID userId);
    OrderDTO getOrderById(Long orderId);
}