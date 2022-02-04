package com.coderhouse.ecommerce.service.impl;

import com.coderhouse.ecommerce.builder.ProductBuilder;
import com.coderhouse.ecommerce.exception.CategoryNotFoundException;
import com.coderhouse.ecommerce.exception.ProductAlreadyExistException;
import com.coderhouse.ecommerce.exception.ProductNotFoundException;
import com.coderhouse.ecommerce.model.request.ProductRequest;
import com.coderhouse.ecommerce.model.response.ProductResponse;
import com.coderhouse.ecommerce.repository.ProductRepository;
import com.coderhouse.ecommerce.service.ProductService;
import com.coderhouse.ecommerce.util.CheckExist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImplementation implements ProductService {

    @Autowired
    private ProductRepository repository;
    @Autowired
    private CheckExist checkExist;


    @Override
    public ProductResponse create(ProductRequest request) throws Exception {
        if(checkExist.product(request.getCode())) {
            throw new ProductAlreadyExistException();
        }

        if(!checkExist.category(request.getCategoryCode())) {
            throw new CategoryNotFoundException();
        }
        var document = repository.save(ProductBuilder.requestToDocument(request));
        return ProductBuilder.documentToResponse(document);
    }

    @Override
    public ProductResponse getByCode(String code) throws Exception {
        if(!checkExist.product(code)){
            throw new ProductNotFoundException();
        }
        return ProductBuilder.documentToResponse(repository.findByCode(code));
    }

    @Override
    public List<ProductResponse> getAll() {
        return ProductBuilder.listDocumentsToListResponse(repository.findAll());
    }

    @Override
    public ProductResponse update(ProductRequest request) throws Exception {
        if(!checkExist.product(request.getCode())) {
            throw new ProductNotFoundException();
        }
        //se quiere actualizar categoría que no existe
        if(!checkExist.category(request.getCategoryCode())) {
            throw new CategoryNotFoundException();
        }

        var document = ProductBuilder.requestToDocument(request);
        //setteo el id de mongodb
        document.setId(repository.findByCode(request.getCode()).getId());

        var documentSaved = repository.save(document);
        return ProductBuilder.documentToResponse(documentSaved);
    }

    @Override
    public ProductResponse delete(String code) throws Exception {
        if(!checkExist.product(code)){
            throw new ProductNotFoundException();
        }
        var document = repository.findByCode(code);
        repository.delete(document);
        return ProductBuilder.documentToResponse(document);
    }
}
