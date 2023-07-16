package com.bitirme.orderservice.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentRequest {
    private final String orderId;
    private final String userId;
    private final Double price;
}
