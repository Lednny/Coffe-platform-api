package com.coffe.platform_api.service;

import com.coffe.platform_api.dto.order.OrderDTO;
import com.coffe.platform_api.dto.order.OrderMapper;
import com.coffe.platform_api.entity.*;
import com.coffe.platform_api.entity.enums.OrderStatus;
import com.coffe.platform_api.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public OrderDTO createOrder(UUID userId) {
    System.out.println("Intentando crear orden para usuario: " + userId);
    Cart cart = cartRepository.findByUserId(userId);
    if (cart == null || cart.getItems().isEmpty()) {
        System.out.println("Carrito vacío o no existe para usuario: " + userId);
        throw new IllegalStateException("El carrito está vacío o no existe");
    }

        Order order = new Order();
        order.setUser(cart.getUser());
        order.setCreatedAt(LocalDateTime.now());
        order.setStatus(OrderStatus.PENDING);

        // Copiar los ítems del carrito a la orden
        List<OrderItem> orderItems = cart.getItems().stream().map(cartItem -> {
            Product product = cartItem.getProduct();
            if (product.getStock() < cartItem.getQuantity()) {
                throw new IllegalArgumentException("No hay stock suficiente para el producto: " + product.getName());
            }
            // Descontar stock
            product.setStock(product.getStock() - cartItem.getQuantity());
            productRepository.save(product);

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(product.getPrice());
            return orderItem;
        }).collect(Collectors.toList());

        order.setItems(orderItems);
        order.setTotal(orderItems.stream().mapToDouble(item -> item.getPrice() * item.getQuantity()).sum());

        // Guardar la orden y los ítems
        orderRepository.save(order);

        // Vaciar el carrito
        cartItemRepository.deleteAll(cart.getItems());

        return OrderMapper.toDTO(order);
    }

    @Override
    public List<OrderDTO> getOrdersForUser(UUID userId) {
        return orderRepository.findByUserId(userId)
                .stream()
                .map(OrderMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDTO getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .map(OrderMapper::toDTO)
                .orElse(null);
    }
}