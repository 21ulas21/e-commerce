package com.bitirme.orderservice.service.Impl;


import com.bitirme.orderservice.model.Cart;
import com.bitirme.orderservice.model.Order;

import com.bitirme.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl {

    private final OrderRepository repository;


    public Order createOrder(Cart cart){
        Order order = new Order();
        order.setSiparisList(cart.getOrderItems());
        order.setUserId(cart.getPersonId());
        order.setTotalPrice(cart.getTotalPrice());
       return repository.save(order);
    }




}
