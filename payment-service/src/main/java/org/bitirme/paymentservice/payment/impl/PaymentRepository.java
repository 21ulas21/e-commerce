package org.bitirme.paymentservice.payment.impl;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, String> {

    List<Payment> findByPaymentStatus(PaymentStatus status);


    Optional<Payment> findByOrderId(String orderId);
}
