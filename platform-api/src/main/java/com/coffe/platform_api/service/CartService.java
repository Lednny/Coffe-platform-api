package com.coffe.platform_api.service;

import com.coffe.platform_api.dto.cart.CartDTO;
import com.coffe.platform_api.dto.cart_item.CartItemDTO;

public interface CartService {
    CartDTO getCartForCurrentUser();
    CartDTO addItemToCart(CartItemDTO item);
    CartDTO removeItemFromCart(Long itemId);
    CartDTO updateItemQuantityInCart(Long itemId, int quantity);
}