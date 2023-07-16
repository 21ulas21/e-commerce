package org.bitirme.paymentservice.payment.impl;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@RequiredArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    private String orderId;
    private String userId;
    private Double price;
    private String userCardId;

}
