package com.bitirme.orderservice.service.Impl;


import com.bitirme.orderservice.dto.CartDto;
import com.bitirme.orderservice.dto.OrderDto;
import com.bitirme.orderservice.model.Cart;
import com.bitirme.orderservice.model.Order;

import com.bitirme.orderservice.model.OrderItems;
import com.bitirme.orderservice.repository.OrderRepository;
import com.bitirme.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;
    private final CartServiceImpl cartService;

    public List<OrderDto> getAllOrder(String personId){
        List<Order> orders = repository.findByPersonId(personId);
        return orders.stream().map(this::toDto).collect(Collectors.toList());

    }


    public OrderDto createOrderEntity(CartDto dto){
        Order order = new Order();
        order.setOrderItems(dto.getOrderItems());
        order.setPersonId(dto.getPersonId());
        order.setTotalPrice(dto.getTotalPrice());
        order.setOrderItems(dto.getOrderItems());
       return toDto(repository.save(order));
    }

    public OrderDto createOrder(String personId){

   CartDto cartDto = cartService.getCartById(personId);
   OrderDto orderDto = createOrderEntity(cartDto);
   return  orderDto;
    }




    public OrderDto toDto(Order order){
        return OrderDto.builder()
                .id(order.getId())
                .orderItems(order.getOrderItems())
                .totalPrice(order.getTotalPrice())
                .personId(order.getPersonId())
                .creatDate(order.getCreatedDate())
                .build();
    }




}
