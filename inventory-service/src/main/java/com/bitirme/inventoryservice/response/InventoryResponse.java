package com.bitirme.inventoryservice.response;

import com.bitirme.inventoryservice.dto.InventoryDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InventoryResponse {
    private String id;
    private String productId;
    private Integer quantity;

    public static InventoryResponse toResponse(InventoryDto dto){
        return InventoryResponse.builder()
                .id(dto.getId())
                .productId(dto.getProductId())
                .quantity(dto.getQuantity())
                .build();
    }


}
