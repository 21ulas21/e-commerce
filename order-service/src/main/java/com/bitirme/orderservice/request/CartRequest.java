package com.bitirme.orderservice.request;

import com.bitirme.orderservice.dto.CartDto;
import com.bitirme.orderservice.model.OrderItems;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
public class CartRequest {
    private String id;
    private List<OrderItems> orderItems;
    private String personId;
    private double totalPrice;

    public CartDto toDto(){
        return CartDto.builder()
                .personId(personId)
                .orderItems(orderItems)
                .id(id)
                .totalPrice(totalPrice)
                .build();
    }


}
