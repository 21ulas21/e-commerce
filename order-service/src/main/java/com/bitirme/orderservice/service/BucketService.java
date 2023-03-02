package com.bitirme.orderservice.service;

import com.bitirme.orderservice.dto.BucketDto;
import com.bitirme.orderservice.dto.ProductDto;
import com.bitirme.orderservice.model.Order;

import java.util.List;

public interface BucketService {
    void createBucket(String userId);
    void removeItem(String productId, String userId);
    String addItem(String productId, Integer quantity, String userId);

    BucketDto getBucket(String userId);
    Order createOrder(String userId);
}
