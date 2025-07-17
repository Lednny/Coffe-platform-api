package com.coffe.platform_api.dto.order;

import com.coffe.platform_api.entity.Order;
import com.coffe.platform_api.dto.order_item.OrderItemMapper;
import com.coffe.platform_api.dto.payment.PaymentMapper;

public class OrderMapper {
    public static OrderDTO toDTO(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setUserId(order.getUser().getId());
        dto.setCreatedAt(order.getCreatedAt());
        dto.setTotal(order.getTotal());
        dto.setStatus(order.getStatus().name());
        dto.setItems(OrderItemMapper.toDTOList(order.getItems()));
        dto.setPayment(PaymentMapper.toDTO(order.getPayment()));
        return dto;
    }
}