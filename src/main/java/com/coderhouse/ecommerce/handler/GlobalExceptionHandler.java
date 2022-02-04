package com.coderhouse.ecommerce.handler;

import com.coderhouse.ecommerce.exception.*;
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
    public ErrorMessage MethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        log.error(ex.getClass().getSimpleName() + " " + ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        return ErrorMessage.of(ex.getClass().getSimpleName(), ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
    }

    @ResponseBody
    @ExceptionHandler (ApiException.class)
    @ResponseStatus (HttpStatus.NOT_FOUND)
    public ErrorMessage ApiException(ApiException ex) {
        log.error(ex.getClass().getSimpleName() + " " + ex.getMessage());
        return ErrorMessage.of(ex.getClass().getSimpleName(), ex.getMessage());
    }


}
