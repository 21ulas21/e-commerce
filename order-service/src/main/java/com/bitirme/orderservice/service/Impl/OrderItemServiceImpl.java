package com.bitirme.orderservice.service.Impl;

import com.bitirme.orderservice.dto.OrderItemsDto;
import com.bitirme.orderservice.dto.ProductDto;
import com.bitirme.orderservice.model.OrderItems;
import org.springframework.stereotype.Service;

@Service
public class OrderItemServiceImpl {


    public OrderItems creatOrderItem(ProductDto productDto, Integer quantity){
        OrderItems orderItems = new OrderItems();
        orderItems.setId("123456");//random String id oluşturulacak
        orderItems.setQuantity(quantity);
        orderItems.setProductId(productDto.getId());
        orderItems.setImageUrl(productDto.getImageUrl());
        orderItems.setProductName(productDto.getName());
        orderItems.setProductPrice(productDto.getPrice());
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
