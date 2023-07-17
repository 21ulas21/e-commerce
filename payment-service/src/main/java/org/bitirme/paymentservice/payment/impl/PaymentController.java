package org.bitirme.paymentservice.payment.impl;

import lombok.RequiredArgsConstructor;
import org.bitirme.paymentservice.payment.api.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService service;

    @PostMapping()
    public Boolean creatPayment(@RequestBody PaymentRequest request){
       return service.createPayment(request.toDto());
    }
    @PutMapping("/{id}")
    public ResponseEntity<PaymentResponse> updatePaymentStatus(@PathVariable(value = "id") String paymentId, @RequestBody PaymentStatus status){
        System.out.println("sadasd");
        var result = service.updatePaymentStatus(paymentId,status);

        return ResponseEntity.ok(PaymentResponse.toResponse(result));
    }
    @GetMapping()
    public ResponseEntity<List<PaymentResponse>> getAllPayment(@RequestBody PaymentStatus status){
        var result = service.getAllPaymentByPaymentStatus(status).stream().map(PaymentResponse::toResponse).toList();
        return ResponseEntity.ok(result);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponse> GetPayment(@PathVariable(value = "id") String orderId){
        var result = service.getPaymentByOrderId(orderId);
        return ResponseEntity.ok(PaymentResponse.toResponse(result));
    }




}
