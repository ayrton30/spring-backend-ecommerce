package com.coderhouse.ecommerce.handler;

import com.coderhouse.ecommerce.exception.CategoryAlreadyExistException;
import com.coderhouse.ecommerce.exception.CategoryNotFoundException;
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
    @ResponseStatus (HttpStatus.BAD_REQUEST)
    public String IdNotFound(CategoryNotFoundException ex) {
        log.error(ex.getMessage());
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler (CategoryAlreadyExistException.class)
    @ResponseStatus (HttpStatus.BAD_REQUEST)
    public String IdNotFound(CategoryAlreadyExistException ex) {
        log.error(ex.getMessage());
        return ex.getMessage();
    }
}
