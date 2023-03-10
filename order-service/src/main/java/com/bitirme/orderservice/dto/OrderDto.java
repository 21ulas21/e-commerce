package com.bitirme.orderservice.dto;


import com.bitirme.orderservice.model.OrderItems;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class OrderDto {

    private String id;
    private Date creatDate;
    private String personId;
    private Double totalPrice;
    private List<OrderItems> orderItems;
}
