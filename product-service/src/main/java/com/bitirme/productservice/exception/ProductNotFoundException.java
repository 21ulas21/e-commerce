package com.bitirme.productservice.exception;

public class ProductNotFoundException extends RuntimeException{

    public ProductNotFoundException(String s){
        super(s);
    }
}
