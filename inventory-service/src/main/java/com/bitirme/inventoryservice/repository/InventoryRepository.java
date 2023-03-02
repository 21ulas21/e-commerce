package com.bitirme.inventoryservice.repository;

import com.bitirme.inventoryservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;



public interface InventoryRepository extends JpaRepository<Inventory,String> {

    Inventory findByProductId(String productId);




}
