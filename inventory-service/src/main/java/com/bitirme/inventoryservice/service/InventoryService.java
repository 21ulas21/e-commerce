package com.bitirme.inventoryservice.service;

import com.bitirme.inventoryservice.dto.InventoryDto;

import java.util.List;

public interface InventoryService {


    List<InventoryDto> getAllStock();
    void createStock(String productId);
    void stockInc(String productId, Integer quantity);
    void stockDec(String productId, Integer quantity);
     InventoryDto stockQuantity(String productId);

}
