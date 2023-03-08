package com.bitirme.orderservice.response;

import com.bitirme.orderservice.dto.OrderDto;
import com.bitirme.orderservice.model.Cart;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
@Data
@Builder
public class OrderResponse {
    private String id;
    private Cart cart;
    private Date creatDate;





}
