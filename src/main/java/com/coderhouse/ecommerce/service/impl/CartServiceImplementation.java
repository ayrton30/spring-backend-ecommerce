package com.coderhouse.ecommerce.service.impl;

import com.coderhouse.ecommerce.builder.CartBuilder;
import com.coderhouse.ecommerce.exception.*;
import com.coderhouse.ecommerce.model.Item;
import com.coderhouse.ecommerce.model.request.CartItem;
import com.coderhouse.ecommerce.model.request.CartRequest;
import com.coderhouse.ecommerce.model.response.CartResponse;
import com.coderhouse.ecommerce.repository.CartRepository;
import com.coderhouse.ecommerce.service.CartService;
import com.coderhouse.ecommerce.util.CheckExist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceImplementation implements CartService {

    @Autowired
    private CartRepository repository;
    @Autowired
    private CheckExist checkExist;


    @Override
    public CartResponse create(CartRequest request) throws Exception {
        if(!checkExist.user(request.getEmail())) {
            throw new UserNotFoundException();
        }

        if(checkExist.cart(request.getEmail())) {
            throw new CartAlreadyExistException();
        }

        var document = repository.save(CartBuilder.requestToDocument(request));
        return CartBuilder.documentToResponse(document);
    }

    @Override
    public CartResponse getCart(String email) throws Exception {
        if(!checkExist.cart(email)) {
            throw new CartNotFoundException();
        }
        return CartBuilder.documentToResponse(repository.findByEmail(email));
    }

    @Override
    public List<CartResponse> getAll() {
        return CartBuilder.listDocumentsToListResponse(repository.findAll());
    }

    @Override
    public CartResponse update(CartRequest request) throws Exception {
        if(!checkExist.cart(request.getEmail())) {
            throw new CartNotFoundException();
        }

        var document = CartBuilder.requestToDocument(request);
        document.setId(repository.findByEmail(request.getEmail()).getId());
        document.setModificationDate(LocalDateTime.now());
        return CartBuilder.documentToResponse(repository.save(document));
    }

    @Override
    public CartResponse delete(String email) throws Exception {
        if(!checkExist.cart(email)) {
            throw new CartNotFoundException();
        }
        var document = repository.findByEmail(email);
        repository.delete(document);
        return CartBuilder.documentToResponse(document);
    }

    @Override
    public CartResponse addItem(CartItem request) throws Exception {
        var email = request.getEmail();
        if(!checkExist.cart(email)) {
            throw new CartNotFoundException();
        }

        var product = request.getItem();
        var codeItem = product.getProductCode();
        if(!checkExist.product(codeItem)) {
            throw new ProductNotFoundException();
        }

        var document = repository.findByEmail(email);
        if(!document.getProducts().isEmpty() &&
                document.getProducts().stream().anyMatch(item -> item.getProductCode().equals(codeItem))) {
            throw new ProductAlreadyInCartException();
        }

        document.getProducts().add(product);
        return CartBuilder.documentToResponse(repository.save(document));
    }

    @Override
    public CartResponse updateItem(CartItem request) throws Exception {
        var email = request.getEmail();
        if(!checkExist.cart(email)) {
            throw new CartNotFoundException();
        }

        var product = request.getItem();
        var codeItem = product.getProductCode();
        var document = repository.findByEmail(email);
        if(!checkExist.product(codeItem) || !document.getProducts().stream().anyMatch(item -> item.getProductCode().equals(codeItem))) {
            throw new ProductNotFoundException();
        }

        document.getProducts().stream().filter(item -> item.getProductCode().equals(codeItem))
                    .findFirst()
                    .get()
                    //si el item ya está en carrito se actualiza el valor de cantidad
                    .setQuantity(product.getQuantity());

        return CartBuilder.documentToResponse(repository.save(document));
    }

    @Override
    public List<Item> getAllItems(String email) throws Exception {
        if(!checkExist.cart(email)) {
            throw new CartNotFoundException();
        }
        return repository.findByEmail(email).getProducts();
    }

    @Override
    public CartResponse deleteItem(String email, String productCode) throws Exception {

        if(!checkExist.cart(email)) {
            throw new CartNotFoundException();
        }

        var document = repository.findByEmail(email);
        if(!checkExist.product(productCode) || !document.getProducts().stream().anyMatch(item -> item.getProductCode().equals(productCode))) {
            throw new ProductNotFoundException();
        }

        var documents = document.getProducts().stream().filter(item -> !item.getProductCode().equals(productCode));
        document.setProducts(documents.collect(Collectors.toList()));
        return CartBuilder.documentToResponse(repository.save(document));
    }
}
