package com.bitirme.orderservice.repository;

import com.bitirme.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, String> {
    List <Order> findByPersonId(String personId);
}
