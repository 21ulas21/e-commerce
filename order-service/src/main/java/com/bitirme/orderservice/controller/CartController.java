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

    @PostMapping("/create")
    public void createCart(@RequestParam String personId){

        cartService.createCart(personId);

    }
    @PreAuthorize("hasAuthority('USER')")
    @PutMapping("/additem")
    public ResponseEntity<String> addItem(@RequestParam String productId,@RequestParam Integer quantity,@RequestParam String personId){

        cartService.addItem(productId,quantity,personId);
        return ResponseEntity.ok("Ürün sepete eklendi");

    }

    @PreAuthorize("hasAuthority('USER')")
    @PutMapping("/removeitem")
    public ResponseEntity<String> removeItem(@RequestParam String productId, @RequestParam String personId){
        cartService.removeItem(productId, personId);
        return ResponseEntity.ok("Ürün sepetten çıkarıldı");


    }
    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/cart")
    public ResponseEntity<CartResponse> getCart(@RequestParam String personId){

        return ResponseEntity.ok(CartResponse.toResponse(cartService.getCart(personId)));

    }






}
