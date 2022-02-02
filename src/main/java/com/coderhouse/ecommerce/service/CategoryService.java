package com.coderhouse.ecommerce.service;

import com.coderhouse.ecommerce.exception.CategoryAlreadyExistException;
import com.coderhouse.ecommerce.exception.CategoryNotFoundException;
import com.coderhouse.ecommerce.model.request.CategoryRequest;
import com.coderhouse.ecommerce.model.response.CategoryResponse;

import java.util.List;

public interface CategoryService {

    CategoryResponse create(CategoryRequest request) throws CategoryAlreadyExistException;
    CategoryResponse getByCode(String code) throws CategoryNotFoundException;
    List<CategoryResponse> getAll();
    CategoryResponse update(CategoryRequest request) throws CategoryNotFoundException;
    CategoryResponse delete(String code) throws CategoryNotFoundException;
}
