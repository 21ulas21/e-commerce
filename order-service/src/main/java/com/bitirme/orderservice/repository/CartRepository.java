package com.bitirme.orderservice.repository;

import com.bitirme.orderservice.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, String> {

   Optional<Cart> findByPersonId(String personId);






}
