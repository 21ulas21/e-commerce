package org.bitirme.paymentservice.payment.impl;

import lombok.Builder;
import lombok.Data;
import org.bitirme.paymentservice.payment.api.PaymentDto;

@Data
@Builder
public class PaymentResponse {

    private final PaymentStatus paymentStatus;
    private final String orderId;
    private final String userId;

    public static PaymentResponse toResponse(PaymentDto payment){
        return PaymentResponse.builder()
                .paymentStatus(payment.getPaymentStatus())
                .orderId(payment.getOrderId())
                .userId(payment.getUserId())
                .build();
    }
}
