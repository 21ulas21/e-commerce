package com.bitirme.orderservice.service.Impl;

import com.bitirme.orderservice.dto.OrderItemsDto;
import com.bitirme.orderservice.dto.ProductDto;
import com.bitirme.orderservice.model.OrderItems;
import com.bitirme.orderservice.repository.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl {
    private final OrderItemRepository repository;


    public OrderItems creatOrderItem(ProductDto productDto, Integer quantity){
        OrderItems orderItems = new OrderItems();

        orderItems.setQuantity(quantity);
        orderItems.setProductId(productDto.getId());
        orderItems.setImageUrl(productDto.getImageUrl());
        orderItems.setProductName(productDto.getName());
        orderItems.setProductPrice(productDto.getPrice());
        repository.save(orderItems);
        return orderItems;

    }

    public OrderItemsDto toDto(OrderItems orderItems){
        return OrderItemsDto.builder()
                .id(orderItems.getId())
                .imageUrl(orderItems.getImageUrl())
                .productId(orderItems.getProductId())
                .productName(orderItems.getProductName())
                .productPrice(orderItems.getProductPrice())
                .quantity(orderItems.getQuantity())
                .build();

    }




}
