package com.bitirme.orderservice.dto;

import com.bitirme.orderservice.model.OrderItems;
import lombok.Builder;
import lombok.Data;


import java.util.List;
@Data
@Builder
public class CartDto {
    private String id;
    private List<OrderItems> orderList;
    private String userId;
    private double totalPrice;
}
