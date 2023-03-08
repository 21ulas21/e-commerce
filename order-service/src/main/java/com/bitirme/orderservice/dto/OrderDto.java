package com.bitirme.orderservice.dto;

import com.bitirme.orderservice.model.Cart;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
@Data
@Builder
public class OrderDto {

    private String id;
    private Cart cart;
    private Date creatDate;
}
