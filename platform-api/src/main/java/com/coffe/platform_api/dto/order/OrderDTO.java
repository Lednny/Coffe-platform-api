package com.coffe.platform_api.dto.order;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.coffe.platform_api.dto.order_item.OrderItemDTO;
import com.coffe.platform_api.dto.payment.PaymentDTO;

public class OrderDTO {
    private Long id;
    private UUID userId;
    private List<OrderItemDTO> items;
    private LocalDateTime createdAt;
    private double total;
    private String status;
    private PaymentDTO payment; 

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public List<OrderItemDTO> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDTO> items) {
        this.items = items;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public PaymentDTO getPayment() {
        return payment;
    }

    public void setPayment(PaymentDTO payment) {
        this.payment = payment;
    }
}