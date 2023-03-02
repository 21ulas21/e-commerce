package com.bitirme.orderservice.request;

import com.bitirme.orderservice.model.OrderItems;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
public class CartRequest {
    private String id;
    private List<OrderItems> orderItems;
    private String userId;
    private double totalPrice;
}
