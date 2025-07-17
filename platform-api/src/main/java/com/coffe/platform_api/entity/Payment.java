package com.coffe.platform_api.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import com.coffe.platform_api.entity.enums.PaymentStatus;

@Entity
@Table(name = "payment", schema = "coffee_schema")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación uno a uno con Order
    @OneToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    private double amount;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    private String paymentMethod; 

    private LocalDateTime paidAt;

    // Constructor vacío requerido por JPA
    public Payment() {}

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Order getOrder() { return order; }
    public void setOrder(Order order) { this.order = order; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public PaymentStatus getStatus() { return status; }
    public void setStatus(PaymentStatus status) { this.status = status; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public LocalDateTime getPaidAt() { return paidAt; }
    public void setPaidAt(LocalDateTime paidAt) { this.paidAt = paidAt; }
}