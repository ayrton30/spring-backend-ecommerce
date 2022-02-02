package com.coderhouse.ecommerce.exception;

public class CategoryAlreadyExistException extends Exception {
    private String msg;

    public CategoryAlreadyExistException(){
        super("Category already exist");
    }
}
