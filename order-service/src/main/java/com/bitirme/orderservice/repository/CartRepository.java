package com.bitirme.orderservice.repository;

import com.bitirme.orderservice.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, String> {

    Cart findByPersonId(String personId);



}
