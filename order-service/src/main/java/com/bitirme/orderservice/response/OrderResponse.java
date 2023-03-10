package com.bitirme.orderservice.response;

import com.bitirme.orderservice.dto.OrderDto;
import com.bitirme.orderservice.model.OrderItems;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class OrderResponse {
    private String id;

    private Date creatDate;
    private String personId;
    private Double totalPrice;
    private List<OrderItems> orderItems;

    public static OrderResponse toResponse(OrderDto dto){
       return OrderResponse.builder()
               .id(dto.getId())
               .creatDate(dto.getCreatDate())
               .personId(dto.getPersonId())
               .orderItems(dto.getOrderItems())
               .totalPrice(dto.getTotalPrice())
               .build();
    }





}
