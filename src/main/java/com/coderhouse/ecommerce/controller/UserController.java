package com.coderhouse.ecommerce.controller;

import com.coderhouse.ecommerce.model.request.UserLogin;
import com.coderhouse.ecommerce.model.request.UserRegister;
import com.coderhouse.ecommerce.model.response.UserResponse;
import com.coderhouse.ecommerce.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("")
    public UserResponse registerUser(@Validated @RequestBody UserRegister request) throws Exception {
        log.info("POST REQUEST ... registerUser | Fecha de ejecución: " + LocalDateTime.now());
        return service.register(request);
    }

    @PostMapping("/login")
    public UserResponse login(@Validated @RequestBody UserLogin request) throws Exception {
        log.info("POST REQUEST ... login | Fecha de ejecución: " + LocalDateTime.now());
        return service.login(request);
    }



}
