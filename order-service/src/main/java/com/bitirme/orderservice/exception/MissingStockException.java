package com.bitirme.orderservice.exception;

public class MissingStockException extends RuntimeException{
    public MissingStockException(String s){
        super(s);
    }
}
