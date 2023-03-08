package com.bitirme.orderservice.service;

import com.bitirme.orderservice.dto.CartDto;
import com.bitirme.orderservice.dto.ProductDto;
import com.bitirme.orderservice.model.Order;

public interface CartService {
    void createBucket(String userId);
    void removeItem(String productId, String userId);
    String addItem(String productId, Integer quantity, String userId);

    CartDto getBucket(String userId);
    Order createOrder(String userId);
}
