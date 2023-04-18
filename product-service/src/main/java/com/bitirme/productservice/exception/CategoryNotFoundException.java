package com.bitirme.productservice.exception;

public class CategoryNotFoundException extends RuntimeException{

    public CategoryNotFoundException(String s){
        super(s);
    }
}
