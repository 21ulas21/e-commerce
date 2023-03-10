package com.bitirme.orderservice.controller;

import com.bitirme.orderservice.dto.OrderDto;
import com.bitirme.orderservice.response.OrderResponse;
import com.bitirme.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    @PostMapping("/createorder")
    public ResponseEntity<OrderResponse> createOrder(@RequestParam String personId){

       OrderDto orderDto = orderService.createOrder(personId);
        return ResponseEntity.ok(OrderResponse.toResponse(orderDto));

    }





}
