package com.coffe.platform_api.dto.order_item;

import com.coffe.platform_api.entity.OrderItem;
import java.util.List;
import java.util.stream.Collectors;

public class OrderItemMapper {
    public static OrderItemDTO toDTO(OrderItem item) {
        OrderItemDTO dto = new OrderItemDTO();
        dto.setId(item.getId());
        dto.setProductId(item.getProduct().getId());
        dto.setProductName(item.getProduct().getName());
        dto.setQuantity(item.getQuantity());
        dto.setPrice(item.getPrice());
        return dto;
    }

    public static List<OrderItemDTO> toDTOList(List<OrderItem> items) {
        return items.stream().map(OrderItemMapper::toDTO).collect(Collectors.toList());
    }
}