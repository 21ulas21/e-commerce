package com.bitirme.orderservice.dto;

import com.bitirme.orderservice.model.OrderItems;
import lombok.Builder;
import lombok.Data;


import java.util.List;
@Data
@Builder
public class BucketDto {
    private String id;
    private List<OrderItems> orderItems;
    private String userId;
    private double totalPrice;
}
