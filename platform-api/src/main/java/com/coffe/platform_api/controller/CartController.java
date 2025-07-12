package com.coffe.platform_api.controller;

import com.coffe.platform_api.dto.cart.CartDTO;
import com.coffe.platform_api.dto.cart_item.CartItemDTO;
import com.coffe.platform_api.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping
    public CartDTO getMyCart() {
        return cartService.getCartForCurrentUser();
    }

    @PostMapping("/add")
    public CartDTO addItemToCart(@RequestBody CartItemDTO item) {
        return cartService.addItemToCart(item);
    }

    @DeleteMapping("/remove/{itemId}")
    public CartDTO removeItemFromCart(@PathVariable Long itemId) {
        return cartService.removeItemFromCart(itemId);
    }

    @PutMapping("/update/{itemId}")
    public CartDTO updateItemQuantity(@PathVariable Long itemId, @RequestParam int quantity) {
        return cartService.updateItemQuantityInCart(itemId, quantity);
    }
}