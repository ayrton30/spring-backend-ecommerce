package com.coderhouse.ecommerce.exception;

public class CategoryNotFoundException extends Exception {
    private String msg;

    public CategoryNotFoundException(){
        super("Category not found");
    }
}
