package com.coderhouse.ecommerce.service;

import com.coderhouse.ecommerce.exception.CategoryNotFoundException;
import com.coderhouse.ecommerce.exception.ProductAlreadyExistException;
import com.coderhouse.ecommerce.exception.ProductNotFoundException;
import com.coderhouse.ecommerce.model.request.ProductRequest;
import com.coderhouse.ecommerce.model.response.ProductResponse;

import java.util.List;

public interface ProductService {

    ProductResponse create(ProductRequest request) throws Exception;
    ProductResponse getByCode(String code) throws Exception;
    List<ProductResponse> getAll();
    ProductResponse update(ProductRequest request) throws Exception;
    ProductResponse delete(String code) throws Exception;
}
