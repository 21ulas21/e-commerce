package com.bitirme.inventoryservice.dto;

import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class InventoryDto {

    private String id;
    private String productId;
    private Integer quantity;
}
