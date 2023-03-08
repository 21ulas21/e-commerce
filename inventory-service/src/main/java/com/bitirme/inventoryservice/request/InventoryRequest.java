package com.bitirme.inventoryservice.request;

import com.bitirme.inventoryservice.dto.InventoryDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InventoryRequest {
    private String id;
    private String productId;
    private Integer quantity;


    public InventoryDto toDto(){
        return InventoryDto.builder()
                .quantity(quantity)
                .id(id)
                .productId(productId)
                .build();
    }


}
