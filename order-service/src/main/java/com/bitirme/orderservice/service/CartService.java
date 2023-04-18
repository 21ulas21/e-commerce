package com.bitirme.orderservice.service;

import com.bitirme.orderservice.dto.CartDto;


public interface CartService {
    void removeItem(String productId);
    String addItem(String productId, Integer quantity);
    CartDto getCart();


}
