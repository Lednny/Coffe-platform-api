package com.coffe.platform_api.dto.payment;

import java.time.LocalDateTime;

public class PaymentDTO {
    private Long id;
    private double amount;
    private String status;
    private String paymentMethod;
    private LocalDateTime paidAt;

    //Constructor vacio
    public PaymentDTO() {
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getPaymentMethod() {
        return paymentMethod;
    }
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    public LocalDateTime getPaidAt() {
        return paidAt;
    }
    public void setPaidAt(LocalDateTime paidAt) {
        this.paidAt = paidAt;
    }
    @Override
    public String toString() {
        return "PaymentDTO{" +
                "id=" + id +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", paidAt=" + paidAt +
                '}';
    }
}