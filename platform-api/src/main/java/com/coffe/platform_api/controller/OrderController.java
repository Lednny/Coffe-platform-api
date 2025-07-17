package com.coffe.platform_api.controller;

import com.coffe.platform_api.dto.order.OrderDTO;
import com.coffe.platform_api.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // Crear una orden desde el carrito
    @PostMapping
    public OrderDTO createOrder() {
        UUID userId = getCurrentUserId();
        return orderService.createOrder(userId);
    }

    // Listar órdenes del usuario autenticado
    @GetMapping
    public List<OrderDTO> getOrdersForUser() {
        UUID userId = getCurrentUserId();
        return orderService.getOrdersForUser(userId);
    }

    // Obtener detalle de una orden
    @GetMapping("/{orderId}")
    public OrderDTO getOrderById(@PathVariable Long orderId) {
        return orderService.getOrderById(orderId);
    }

    private UUID getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal instanceof com.coffe.platform_api.security.user.CustomUserDetails userDetails) {
            return userDetails.getId();
        }
        throw new IllegalStateException("No se pudo obtener el usuario autenticado");
    }
}