package com.coderhouse.ecommerce.controller;

import com.coderhouse.ecommerce.exception.CategoryAlreadyExistException;
import com.coderhouse.ecommerce.exception.CategoryNotFoundException;
import com.coderhouse.ecommerce.model.request.CategoryRequest;
import com.coderhouse.ecommerce.model.response.CategoryResponse;
import com.coderhouse.ecommerce.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @GetMapping("/{code}")
    public CategoryResponse getCategoryByCode(@PathVariable String code) throws CategoryNotFoundException {
        log.info("GET REQUEST ... getCategoryByCode | Fecha de ejecución: " + LocalDateTime.now());
        return service.getByCode(code);
    }

    @GetMapping("/all")
    public List<CategoryResponse> getAllCategories() {
        log.info("GET REQUEST ... getAllCategories | Fecha de ejecución: " + LocalDateTime.now());
        return service.getAll();
    }

    @PostMapping("")
    public CategoryResponse createCategory(@Validated @RequestBody CategoryRequest request) throws CategoryAlreadyExistException {
        log.info("POST REQUEST ... createCategory | Fecha de ejecución: " + LocalDateTime.now());
        return service.create(request);
    }

    @PutMapping("")
    public CategoryResponse updateCategory(@Validated @RequestBody CategoryRequest request) throws CategoryNotFoundException {
        log.info("UPDATE REQUEST ... updateCategory | Fecha de ejecución: " + LocalDateTime.now());
        return service.update(request);
    }

    @DeleteMapping("/{code}")
    public CategoryResponse deleteCategory(@PathVariable String code) throws CategoryNotFoundException {
        log.info("DELETE REQUEST ... deleteCategory | Fecha de ejecución: " + LocalDateTime.now());
        return service.delete(code);
    }

}
