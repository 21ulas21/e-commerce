package com.bitirme.orderservice.repository;

import com.bitirme.orderservice.model.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItems, String> {
}
