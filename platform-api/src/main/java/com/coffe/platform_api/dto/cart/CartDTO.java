package com.coffe.platform_api.dto.cart;

import java.util.List;
import com.coffe.platform_api.dto.cart_item.CartItemDTO;

public class CartDTO {
    private Long id;
    private List<CartItemDTO> items;

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<CartItemDTO> getItems() {
        return items;
    }

    public void setItems(List<CartItemDTO> items) {
        this.items = items;
    }

}
