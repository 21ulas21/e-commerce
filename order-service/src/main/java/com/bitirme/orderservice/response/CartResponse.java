package com.bitirme.orderservice.response;

import com.bitirme.orderservice.dto.CartDto;
import com.bitirme.orderservice.model.OrderItems;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
public class CartResponse {
    private String id;
    private List<OrderItems> orderItems;
    private String personId;
    private double totalPrice;


    public static CartResponse toResponse(CartDto dto) {

        return CartResponse.builder()
                .id(dto.getId())
                .orderItems(dto.getOrderItems())
                .personId(dto.getPersonId())
                .totalPrice(dto.getTotalPrice())
                .build();


    }
}