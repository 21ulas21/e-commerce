package com.bitirme.orderservice.controller;


import com.bitirme.orderservice.response.CartResponse;
import com.bitirme.orderservice.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class CartController {

    private final CartService cartService;


    @PreAuthorize("hasAuthority('USER')")
    @PutMapping("/additem")
    public ResponseEntity<String> addItem(@RequestParam String productId,@RequestParam Integer quantity){


        return ResponseEntity.ok(cartService.addItem(productId,quantity));

    }

    @PreAuthorize("hasAuthority('USER')")
    @PutMapping("/removeitem")
    public ResponseEntity<String> removeItem(@RequestParam String productId){
        cartService.removeItem(productId);
        return ResponseEntity.ok("Ürün sepetten çıkarıldı");


    }
    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/cart")
    public ResponseEntity<CartResponse> getCart(){

        return ResponseEntity.ok(CartResponse.toResponse(cartService.getCart()));

    }






}
