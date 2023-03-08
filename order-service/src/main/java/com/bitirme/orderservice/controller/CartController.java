package com.bitirme.orderservice.controller;


import com.bitirme.orderservice.dto.CartDto;
import com.bitirme.orderservice.model.Order;

import com.bitirme.orderservice.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class CartController {

    private final CartService cartService;

    @PostMapping("/create")
    public void createCart(@RequestBody String personId){

        cartService.createBucket(personId);

    }
    @PostMapping("/additemscart")
    public String addItem(@RequestParam String productId,@RequestParam Integer quantity,@RequestParam String personId){

        cartService.addItem(productId,quantity,personId);

        return "Ürün sepete eklendi";
    }
    @PostMapping("/removeItem/{productId}")
    public String removeItem(@PathVariable String productId, @RequestBody String personId){
        cartService.removeItem(productId, personId);
        return "Ürün spetten çıkarıldı";

    }
    @GetMapping("/bucket")
    public CartDto getCart(@RequestBody String personId){
      return  cartService.getBucket(personId);
    }
    @PostMapping("/siparis")
    public Order siparis(@RequestBody String personId){
        return cartService.createOrder(personId);

    }



}
