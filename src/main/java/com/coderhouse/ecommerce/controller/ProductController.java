package com.coderhouse.ecommerce.controller;

import com.coderhouse.ecommerce.exception.CategoryNotFoundException;
import com.coderhouse.ecommerce.exception.ProductAlreadyExistException;
import com.coderhouse.ecommerce.exception.ProductNotFoundException;
import com.coderhouse.ecommerce.model.request.ProductRequest;
import com.coderhouse.ecommerce.model.response.ProductResponse;
import com.coderhouse.ecommerce.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/{code}")
    public ProductResponse getProductByCode(@PathVariable String code) throws Exception {
        log.info("GET REQUEST ... getProductByCode | Fecha de ejecución: " + LocalDateTime.now());
        return service.getByCode(code);
    }

    @GetMapping("/all")
    public List<ProductResponse> getAllProducts() {
        log.info("GET REQUEST ... getAllProducts | Fecha de ejecución: " + LocalDateTime.now());
        return service.getAll();
    }

    @PostMapping("")
    public ProductResponse createProduct(@Validated @RequestBody ProductRequest request) throws Exception {
        log.info("POST REQUEST ... createProduct | Fecha de ejecución: " + LocalDateTime.now());
        return service.create(request);
    }

    @PutMapping("")
    public ProductResponse updateProduct(@Validated @RequestBody ProductRequest request) throws Exception {
        log.info("UPDATE REQUEST ... updateProduct | Fecha de ejecución: " + LocalDateTime.now());
        return service.update(request);
    }

    @DeleteMapping("/{code}")
    public ProductResponse deleteProduct(@PathVariable String code) throws Exception {
        log.info("DELETE REQUEST ... deleteProduct | Fecha de ejecución: " + LocalDateTime.now());
        return service.delete(code);
    }

}
