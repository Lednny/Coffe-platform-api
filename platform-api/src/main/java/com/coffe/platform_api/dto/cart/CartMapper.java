package com.coffe.platform_api.dto.cart;

import com.coffe.platform_api.dto.cart_item.CartItemDTO;
import com.coffe.platform_api.entity.Cart;
import com.coffe.platform_api.entity.CartItem;
import java.util.stream.Collectors;

public class CartMapper {
    public static CartDTO toDTO(Cart cart) {
        if (cart == null) return null;
        CartDTO dto = new CartDTO();
        dto.setId(cart.getId());
        dto.setItems(cart.getItems().stream().map(CartMapper::toItemDTO).collect(Collectors.toList()));
        return dto;
    }

    public static CartItemDTO toItemDTO(CartItem item) {
        if (item == null) return null;
        CartItemDTO dto = new CartItemDTO();
        dto.setId(item.getId());
        dto.setProductId(item.getProduct().getId());
        dto.setProductName(item.getProduct().getName());
        dto.setProductDescription(item.getProduct().getDescription());
        dto.setProductImageUrl(item.getProduct().getImageUrl());
        dto.setProductPrice(item.getProduct().getPrice());
        dto.setProductCategory(item.getProduct().getCategory().getName());
        dto.setQuantity(item.getQuantity());
        return dto;
    }
}
