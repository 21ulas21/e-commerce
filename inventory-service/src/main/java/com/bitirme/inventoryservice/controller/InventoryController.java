package com.bitirme.inventoryservice.controller;

import com.bitirme.inventoryservice.dto.InventoryDto;

import com.bitirme.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/getAll")
    public List<InventoryDto> getAllStock(){

       return inventoryService.getAllStock();

    }
    @PostMapping("/create")
    public ResponseEntity<String> createStock(@RequestBody String productId){

        inventoryService.createStock(productId);
        return ResponseEntity.ok("Stock oluşturuldu");
    }

    @PutMapping("/incstock")
    public void incStock(@RequestBody String productId, @RequestBody Integer quantity){

        inventoryService.stockInc(productId, quantity);
    }

    @PutMapping("/decstock")
    public void decStock(@RequestBody String productId, @RequestBody Integer quantity){
        inventoryService.stockDec(productId, quantity);
    }

    @GetMapping("/{productId}")
    public InventoryDto stockQuantity(@PathVariable String productId){
       return inventoryService.stockQuantity(productId);

    }



}
