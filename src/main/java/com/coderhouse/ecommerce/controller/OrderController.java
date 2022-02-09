package com.coderhouse.ecommerce.controller;

import com.coderhouse.ecommerce.model.request.OrderRequest;
import com.coderhouse.ecommerce.model.response.OrderResponse;
import com.coderhouse.ecommerce.service.OrderService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService service;

    @PostMapping("/buy")
    public OrderResponse createOrder(@Validated @RequestBody OrderRequest request) throws Exception {
        log.info("POST REQUEST ... createOrder | Fecha de ejecución: " + LocalDateTime.now());
        return service.create(request);
    }

    @GetMapping("/all")
    public List<OrderResponse> getAllOrders() {
        log.info("GET REQUEST ... getAllOrders | Fecha de ejecución: " + LocalDateTime.now());
        return service.getAll();
    }
}
