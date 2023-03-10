package com.bitirme.orderservice.service;

import com.bitirme.orderservice.dto.CartDto;


public interface CartService {
    void createCart(String personId);
    void removeItem(String productId, String personId);
    String addItem(String productId, Integer quantity, String personId);
    CartDto getCart(String personId);

}
