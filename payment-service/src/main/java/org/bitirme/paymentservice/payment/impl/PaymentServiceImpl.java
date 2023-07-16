package org.bitirme.paymentservice.payment.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.bitirme.paymentservice.payment.api.PaymentDto;
import org.bitirme.paymentservice.payment.api.PaymentService;
import org.bitirme.paymentservice.usercard.api.UserCardDto;
import org.bitirme.paymentservice.usercard.impl.UserCardServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository repository;
    private final UserCardServiceImpl userCardService;


    @Override
    public boolean createPayment(PaymentDto dto) {
        Payment payment = toEntity(new Payment(), dto);
       return payOrder(userCardService.getCard(payment.getUserCardId()), payment.getPrice());

    }

    @Override
    public PaymentDto updatePaymentStatus(String paymentId, PaymentStatus status) {
       Payment payment = repository.findById(paymentId).orElseThrow(EntityNotFoundException::new);
            payment.setPaymentStatus(status);
      return toDto(repository.save(payment));
    }

    @Override
    public List<PaymentDto> getAllPaymentByPaymentStatus(PaymentStatus status) {
        return repository.findByPaymentStatus(status).stream().map(this::toDto).toList();
    }

    @Override
    public PaymentDto getPaymentByOrderId(String orderId) {
        Payment payment = repository.findByOrderId(orderId).orElseThrow(EntityNotFoundException::new);
        return toDto(payment);
    }


    private boolean payOrder(UserCardDto userCard, Double price){

        System.out.println("Ödeme işlemi gerçekleştirildi");

        return true;

    }
    public Payment toEntity(Payment payment, PaymentDto dto){
        payment.setUserId(dto.getUserId());
        payment.setPrice(dto.getPrice());
        payment.setOrderId(dto.getOrderId());
        payment.setUserCardId(userCardService.getUserCardByUserId(dto.getUserId()).getId());
        payment.setPaymentStatus(PaymentStatus.Beklemede);
        return payment;
    }
    private PaymentDto toDto(Payment payment){
        return PaymentDto.builder()
                .price(payment.getPrice())
                .userId(payment.getUserId())
                .orderId(payment.getOrderId())
                .id(payment.getId())
                .paymentStatus(payment.getPaymentStatus())
                .userCardId(payment.getUserCardId())
                .build();
    }




}
