package com.coderhouse.ecommerce.service.impl;

import com.coderhouse.ecommerce.builder.CategoryBuilder;
import com.coderhouse.ecommerce.exception.CategoryAlreadyExistException;
import com.coderhouse.ecommerce.exception.CategoryNotFoundException;
import com.coderhouse.ecommerce.model.request.CategoryRequest;
import com.coderhouse.ecommerce.model.response.CategoryResponse;
import com.coderhouse.ecommerce.repository.CategoryRepository;
import com.coderhouse.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImplementation implements CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Override
    public CategoryResponse create(CategoryRequest request) throws CategoryAlreadyExistException {
        if(categoryExists(request.getCode())) {
            throw new CategoryAlreadyExistException();
        }
        var document = repository.save(CategoryBuilder.requestToDocument(request));
        return CategoryBuilder.documentToResponse(document);
    }

    @Override
    public CategoryResponse getByCode(String code) throws CategoryNotFoundException {
        if(!categoryExists(code)){
            throw new CategoryNotFoundException();
        }
        return CategoryBuilder.documentToResponse(repository.findByCode(code));
    }

    @Override
    public List<CategoryResponse> getAll() {
        return CategoryBuilder.listDocumentsToListResponse(repository.findAll());
    }

    @Override
    public CategoryResponse update(CategoryRequest request) throws CategoryNotFoundException {
        if(!categoryExists(request.getCode())){
            throw new CategoryNotFoundException();
        }
        var document = CategoryBuilder.requestToDocument(request);
        //setteo el id de mongodb
        document.setId(repository.findByCode(request.getCode()).getId());

        var documentSaved = repository.save(document);
        return CategoryBuilder.documentToResponse(documentSaved);
    }

    @Override
    public CategoryResponse delete(String code) throws CategoryNotFoundException {
        if(!categoryExists(code)){
            throw new CategoryNotFoundException();
        }
        var document = repository.findByCode(code);
        repository.delete(document);
        return CategoryBuilder.documentToResponse(document);
    }

    private boolean categoryExists(String code) {
        return repository.existsByCode(code);
    }
}
