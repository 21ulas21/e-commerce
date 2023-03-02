package com.bitirme.orderservice.controller;

import com.bitirme.orderservice.dto.BucketDto;
import com.bitirme.orderservice.model.Order;
import com.bitirme.orderservice.service.BucketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class BucketController {

    private final BucketService bucketService;

    @PostMapping("/create")
    public void createBasket(@RequestBody String userId){

        bucketService.createBucket(userId);

    }
    @PostMapping("/additemsbucket")
    public String addItem(@RequestParam String productId,@RequestParam Integer quantity,@RequestParam String userId){

        bucketService.addItem(productId,quantity,userId);

        return "Ürün sepete eklendi";
    }
    @PostMapping("/removeItem/{productId}")
    public String removeItem(@PathVariable String productId, @RequestBody String userId){
        bucketService.removeItem(productId, userId);
        return "Ürün spetten çıkarıldı";

    }
    @GetMapping("/bucket")
    public BucketDto getBucket(@RequestBody String userId){
      return  bucketService.getBucket(userId);
    }
    @PostMapping("/siparis")
    public Order siparis(@RequestBody String userId){
        return bucketService.createOrder(userId);

    }



}
