package com.coderhouse.ecommerce.exception;

public class ProductAlreadyExistException extends Exception {
    private String msg;

    public ProductAlreadyExistException(){
        super("Product already exist");
    }
}
