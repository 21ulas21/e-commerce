package com.bitirme.orderservice.request;

import com.bitirme.orderservice.dto.OrderDto;
import com.bitirme.orderservice.model.Cart;
import com.bitirme.orderservice.model.OrderItems;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class OrderRequest {
    private String id;
    private Date creatDate;
    private String personId;
    private Double totalPrice;
    private List<OrderItems> orderItems;

    public OrderDto toDto(){
        return OrderDto.builder()
                .id(id)
                .creatDate(creatDate)
                .personId(personId)
                .totalPrice(totalPrice)
                .orderItems(orderItems)
                .build();
    }
}
