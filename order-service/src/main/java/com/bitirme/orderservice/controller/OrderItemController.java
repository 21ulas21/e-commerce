package com.bitirme.orderservice.controller;

import com.bitirme.orderservice.model.OrderItems;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderItemController {



    public void creatItemList(@PathVariable String productId, @RequestBody Integer quantity){


    }



}
