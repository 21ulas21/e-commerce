package com.bitirme.orderservice.controller;

import com.bitirme.orderservice.dto.OrderDto;
import com.bitirme.orderservice.response.OrderResponse;
import com.bitirme.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart/order")
public class OrderController {
    private final OrderService orderService;
    @PreAuthorize("hasAuthority('USER')")
    @PostMapping()
    public ResponseEntity<OrderResponse> createOrder(){
       OrderDto orderDto = orderService.createOrder();
        return ResponseEntity.ok(OrderResponse.toResponse(orderDto));
    }
    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOrder(){
        List<OrderResponse> orderResponses = orderService.getAllOrder().stream().map(OrderResponse::toResponse).toList();
        return ResponseEntity.ok(orderResponses);


    }





}
