package org.bitirme.paymentservice.payment.impl;

import lombok.Builder;
import lombok.Data;
import org.bitirme.paymentservice.payment.api.PaymentDto;

@Data
@Builder
public class PaymentRequest {

    private final String orderId;
    private final String userId;
    private final Double price;

    public PaymentDto toDto(){
        return PaymentDto.builder()
                .orderId(orderId)
                .userId(userId)
                .price(price)
                .build();
    }


}
