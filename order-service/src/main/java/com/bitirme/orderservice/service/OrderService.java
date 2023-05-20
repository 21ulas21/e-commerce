package com.bitirme.orderservice.service;

import com.bitirme.orderservice.dto.OrderDto;

import java.util.List;

public interface OrderService {
    OrderDto createOrder();
    List<OrderDto> getAllOrder(String personId);
}
