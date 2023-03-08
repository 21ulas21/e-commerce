package com.bitirme.inventoryservice.service.impl;

import com.bitirme.inventoryservice.dto.InventoryDto;
import com.bitirme.inventoryservice.model.Inventory;
import com.bitirme.inventoryservice.repository.InventoryRepository;
import com.bitirme.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository repository;


    public List<InventoryDto> getAllStock(){

       return repository.findAll().stream().filter(inventory -> inventory.getQuantity()>0)
               .map(this::mapToDto).collect(Collectors.toList());
    }

    public void createStock(String productId){

        Inventory inventory = new Inventory();
        inventory.setProductId(productId);

        inventory.setQuantity(0);
        repository.save(inventory);
    }

    public void stockInc(String productId, Integer quantity){

        Inventory inventory = repository.findByProductId(productId);
        if (inventory != null){
            inventory.setQuantity(inventory.getQuantity()+quantity);
        }
        repository.save(inventory);

    }

    public void stockDec(String productId, Integer quantity){

        Inventory inventory = repository.findByProductId(productId);

        if (inventory != null && inventory.getQuantity()>=quantity){
            inventory.setQuantity(inventory.getQuantity()-quantity);
        }

        repository.save(inventory);

    }

    public InventoryDto stockQuantity(String productId) {
        Inventory inventory = repository.findByProductId(productId);
        if (inventory != null) {
            return  mapToDto(inventory);
        }
        throw new RuntimeException("ürün bulunamadı");
    }

    public InventoryDto mapToDto(Inventory inventory){

         return  InventoryDto.builder()
                .id(inventory.getId())
                .quantity(inventory.getQuantity())
                .productId(inventory.getProductId())
                .build();
    }



}
