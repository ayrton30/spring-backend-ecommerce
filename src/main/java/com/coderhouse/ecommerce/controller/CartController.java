package com.coderhouse.ecommerce.controller;

import com.coderhouse.ecommerce.model.Item;
import com.coderhouse.ecommerce.model.request.CartItem;
import com.coderhouse.ecommerce.model.request.CartRequest;
import com.coderhouse.ecommerce.model.response.CartResponse;
import com.coderhouse.ecommerce.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService service;

    @PostMapping("")
    public CartResponse createCart(@Validated @RequestBody CartRequest request) throws Exception {
        log.info("POST REQUEST ... createCart | Fecha de ejecución: " + LocalDateTime.now());
        return service.create(request);
    }

    @GetMapping("/{email}")
    public CartResponse getCartByEmail(@PathVariable String email) throws Exception {
        log.info("GET REQUEST ... getCartByEmail | Fecha de ejecución: " + LocalDateTime.now());
        return service.getCart(email);
    }

    @GetMapping("/all")
    public List<CartResponse> getAll() {
        log.info("GET REQUEST ... getAll | Fecha de ejecución: " + LocalDateTime.now());
        return service.getAll();
    }

    @PutMapping("")
    public CartResponse updateCart(@Validated @RequestBody CartRequest request) throws Exception {
        log.info("PUT REQUEST ... updateCart | Fecha de ejecución: " + LocalDateTime.now());
        return service.update(request);
    }

    @DeleteMapping("/{email}")
    public CartResponse deleteCart(@PathVariable String email) throws Exception {
        log.info("DELETE REQUEST ... deleteCart | Fecha de ejecución: " + LocalDateTime.now());
        return service.delete(email);
    }

    //Items
    @PostMapping("/item")
    public CartResponse addItem(@RequestBody @Validated CartItem request) throws Exception{
        log.info("POST REQUEST ... addItem | Fecha de ejecución: " + LocalDateTime.now());
        return service.addItem(request);
    }

    @PutMapping("/item")
    public CartResponse updateItem(@Validated @RequestBody CartItem request) throws Exception {
        log.info("PUT REQUEST ... updateItem | Fecha de ejecución: " + LocalDateTime.now());
        return service.updateItem(request);
    }

    @DeleteMapping("/item/{email}/{productCode}")
    public CartResponse deleteItem(@PathVariable String email, @PathVariable String productCode) throws Exception {
        log.info("DELETE REQUEST ... deleteItem | Fecha de ejecución: " + LocalDateTime.now());
        return service.deleteItem(email, productCode);
    }

    @GetMapping("/item/{email}")
    public List<Item> getItems(@PathVariable String email) throws Exception {
        log.info("GET REQUEST ... getItems | Fecha de ejecución: " + LocalDateTime.now());
        return service.getAllItems(email);
    }

}
