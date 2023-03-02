package com.bitirme.orderservice.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public class OrderController {

    public void createOrder() {
        // sepet oluşturma
        //service katmanında ürün stock kontrolü yapılır
        // ürünlerin listesini geri döner
        //ve sepet idsi geri döner


    }
    public void siparis(){
        //parametre olarak sepet idsi alır
        //id veritabanında aratılır ve kayıtlı ürünler stocktan düşürülür
        // kullanıcıya sipariş verildi mesajı döner



    }
    public void creatItem(@PathVariable String productId, @RequestBody Integer quantity){


    }

}
