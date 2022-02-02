package com.coderhouse.ecommerce.service;

import com.coderhouse.ecommerce.exception.CategoryNotFoundException;
import com.coderhouse.ecommerce.exception.ProductAlreadyExistException;
import com.coderhouse.ecommerce.exception.ProductNotFoundException;
import com.coderhouse.ecommerce.model.request.ProductRequest;
import com.coderhouse.ecommerce.model.response.ProductResponse;

import java.util.List;

public interface ProductService {

    ProductResponse create(ProductRequest request) throws ProductAlreadyExistException, CategoryNotFoundException;
    ProductResponse getByCode(String code) throws ProductNotFoundException;
    List<ProductResponse> getAll();
    ProductResponse update(ProductRequest request) throws ProductNotFoundException, CategoryNotFoundException;
    ProductResponse delete(String code) throws ProductNotFoundException;
}
