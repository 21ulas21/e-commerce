package com.bitirme.orderservice.service.Impl;


import com.bitirme.orderservice.model.Bucket;
import com.bitirme.orderservice.model.Order;
import com.bitirme.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl {

    private final OrderRepository repository;


    public Order createOrder(Bucket bucket){
        Order order = new Order();
        order.setBucket(bucket);
       return repository.save(order);
    }




}
