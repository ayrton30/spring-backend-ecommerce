package com.coderhouse.ecommerce.exception;

public class NotEqualPasswordsException extends ApiException{
    public NotEqualPasswordsException(){
        super("Passwords are not equal");
    }
}
