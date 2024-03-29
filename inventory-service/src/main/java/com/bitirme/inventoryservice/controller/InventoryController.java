package com.bitirme.inventoryservice.controller;

import com.bitirme.inventoryservice.dto.InventoryDto;

import com.bitirme.inventoryservice.response.InventoryResponse;
import com.bitirme.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;



import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/inventory")

public class InventoryController {

    private final InventoryService inventoryService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/getAll")
    public ResponseEntity<List<InventoryResponse>> getAllStock(){

        List<InventoryResponse> inventoryResponseList = toResponse(inventoryService.getAllStock());
        return ResponseEntity.ok(inventoryResponseList);

    }

    @PreAuthorize("hasAnyAuthority('ADMIN','SERVICE')")
    @PostMapping("/create/{productId}")
    public ResponseEntity<String> createStock(@PathVariable String productId){

        inventoryService.createStock(productId);
        return ResponseEntity.ok("Stock oluşturuldu");
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','SERVICE')")
    @PutMapping("/incstock")
    public void incStock(@RequestParam String productId, @RequestParam Integer quantity){

        inventoryService.stockInc(productId, quantity);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','SERVICE')")
    @PutMapping("/decstock")
    public void decStock(@RequestParam String productId, @RequestParam Integer quantity){
        System.out.println("decstock");
        inventoryService.stockDec(productId, quantity);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','SERVICE')")
    @GetMapping("/{productId}")
    public InventoryResponse stockQuantity(@PathVariable String productId){

            return InventoryResponse.toResponse(inventoryService.stockQuantity(productId));

    }

    public List<InventoryResponse> toResponse(List<InventoryDto> inventoryDtoList){

        return inventoryDtoList.stream().map(InventoryResponse::toResponse).toList();
    }



}
