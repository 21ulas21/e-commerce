package org.bitirme.paymentservice.payment.api;

import org.bitirme.paymentservice.payment.impl.PaymentRequest;
import org.bitirme.paymentservice.payment.impl.PaymentStatus;
import org.bitirme.paymentservice.payment.impl.PaymentResponse;

import java.util.List;

public interface PaymentService {

    boolean createPayment(PaymentDto dto);

    PaymentDto updatePaymentStatus(String paymentId, PaymentStatus status);

    List<PaymentDto> getAllPaymentByPaymentStatus(PaymentStatus status);

    PaymentDto getPaymentByOrderId(String orderId);


}
