package com.bitirme.inventoryservice.exception;

public class StockNotFoundException extends RuntimeException{

    public StockNotFoundException(String s){
        super(s);
    }

}
