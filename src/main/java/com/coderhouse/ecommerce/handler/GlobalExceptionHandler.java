package com.coderhouse.ecommerce.handler;

import com.coderhouse.ecommerce.exception.CategoryAlreadyExistException;
import com.coderhouse.ecommerce.exception.CategoryNotFoundException;
import com.coderhouse.ecommerce.exception.ProductAlreadyExistException;
import com.coderhouse.ecommerce.exception.ProductNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler (MethodArgumentNotValidException.class)
    @ResponseStatus (HttpStatus.BAD_REQUEST)
    public String MethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.error(ex.getMessage());
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler (CategoryNotFoundException.class)
    @ResponseStatus (HttpStatus.NOT_FOUND)
    public String CNotFound(CategoryNotFoundException ex) {
        log.error(ex.getMessage());
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler (CategoryAlreadyExistException.class)
    @ResponseStatus (HttpStatus.CONFLICT)
    public String CAlreadyExist(CategoryAlreadyExistException ex) {
        log.error(ex.getMessage());
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler (ProductNotFoundException.class)
    @ResponseStatus (HttpStatus.NOT_FOUND)
    public String PNotFound(ProductNotFoundException ex) {
        log.error(ex.getMessage());
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler (ProductAlreadyExistException.class)
    @ResponseStatus (HttpStatus.CONFLICT)
    public String PAlreadyExist(ProductAlreadyExistException ex) {
        log.error(ex.getMessage());
        return ex.getMessage();
    }
}
