package org.bitirme.paymentservice.payment.api;

import lombok.Builder;
import lombok.Data;
import org.bitirme.paymentservice.payment.impl.PaymentStatus;


@Data
@Builder
public class PaymentDto {
    private final String id;
    private final String orderId;
    private final String userId;
    private final Double price;
    private final PaymentStatus paymentStatus;
    private final String userCardId;
}
