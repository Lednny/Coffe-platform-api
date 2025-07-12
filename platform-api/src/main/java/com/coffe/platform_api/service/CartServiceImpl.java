package com.coffe.platform_api.service;

import com.coffe.platform_api.dto.cart.CartDTO;
import com.coffe.platform_api.dto.cart_item.CartItemDTO;
import com.coffe.platform_api.dto.cart.CartMapper;
import com.coffe.platform_api.entity.Cart;
import com.coffe.platform_api.entity.CartItem;
import com.coffe.platform_api.entity.Product;
import com.coffe.platform_api.entity.User;
import com.coffe.platform_api.repository.CartItemRepository;
import com.coffe.platform_api.repository.CartRepository;
import com.coffe.platform_api.repository.ProductRepository;
import com.coffe.platform_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;

    private User getAuthenticatedUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(username);
    }

    @Override
    public CartDTO getCartForCurrentUser() {
        User user = getAuthenticatedUser();
        Cart cart = cartRepository.findByUserId(user.getId());
        if (cart == null) {
            cart = new Cart();
            cart.setUser(user);
            cart = cartRepository.save(cart);
        }
        return CartMapper.toDTO(cart);
    }

@Override
public CartDTO addItemToCart(CartItemDTO itemDTO) {
    User user = getAuthenticatedUser();
    Cart cart = cartRepository.findByUserId(user.getId());
    if (cart == null) {
        cart = new Cart();
        cart.setUser(user);
        cart = cartRepository.save(cart);
    }
    Optional<Product> productOpt = productRepository.findById(itemDTO.getProductId());
    if (productOpt.isEmpty()) throw new RuntimeException("Producto no encontrado");

    // Buscar si ya existe el item en el carrito
    CartItem existing = cart.getItems().stream()
            .filter(ci -> ci.getProduct().getId().equals(itemDTO.getProductId()))
            .findFirst().orElse(null);

    if (existing != null) {
        existing.setQuantity(existing.getQuantity() + itemDTO.getQuantity());
        cartItemRepository.save(existing);
    } else {
        CartItem newItem = new CartItem();
        newItem.setCart(cart);
        newItem.setProduct(productOpt.get());
        newItem.setQuantity(itemDTO.getQuantity());
        cartItemRepository.save(newItem);
    }
    return getCartForCurrentUser();
}


    @Override
    public CartDTO removeItemFromCart(Long itemId) {
        cartItemRepository.deleteById(itemId);
        return getCartForCurrentUser();
    }

@Override
public CartDTO updateItemQuantityInCart(Long itemId, int quantity) {
    if (quantity <= 0) {
        throw new IllegalArgumentException("La cantidad debe ser mayor a cero.");
    }
    Optional<CartItem> itemOpt = cartItemRepository.findById(itemId);
    if (itemOpt.isPresent()) {
        CartItem item = itemOpt.get();
        item.setQuantity(quantity);
        cartItemRepository.save(item);
    }
    return getCartForCurrentUser();
}
}