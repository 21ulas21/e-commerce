package com.bitirme.orderservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderItemsDto {
    private String id;
    private String productId;
    private String productName;
    private Double productPrice;
    private String imageUrl;
    private Integer quantity;
}
