package com.coderhouse.ecommerce.exception;

public class ProductNotFoundException extends Exception {
    private String msg;

    public ProductNotFoundException(){
        super("Product not found");
    }
}
