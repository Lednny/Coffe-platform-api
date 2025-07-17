package com.coffe.platform_api.dto.payment;

import com.coffe.platform_api.entity.Payment;

public class PaymentMapper {
    public static PaymentDTO toDTO(Payment payment) {
        if (payment == null) return null;
        PaymentDTO dto = new PaymentDTO();
        dto.setId(payment.getId());
        dto.setAmount(payment.getAmount());
        dto.setStatus(payment.getStatus().name());
        dto.setPaymentMethod(payment.getPaymentMethod());
        dto.setPaidAt(payment.getPaidAt());
        return dto;
    }
}