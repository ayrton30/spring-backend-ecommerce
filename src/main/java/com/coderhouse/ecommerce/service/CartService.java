package com.coderhouse.ecommerce.service;

import com.coderhouse.ecommerce.model.Item;
import com.coderhouse.ecommerce.model.request.CartItem;
import com.coderhouse.ecommerce.model.request.CartRequest;
import com.coderhouse.ecommerce.model.response.CartResponse;

import java.util.List;

public interface CartService {

    CartResponse create(CartRequest request) throws Exception;
    CartResponse getCart(String email) throws Exception;
    List<CartResponse> getAll();
    CartResponse update(CartRequest request) throws Exception;
    CartResponse delete(String email) throws Exception;

    //se suman cantidades si agregamos un mismo producto a un mismo carrito
    public CartResponse addItem(CartItem request) throws Exception;
    public CartResponse updateItem(CartItem request) throws Exception;
    List<Item> getAllItems(String email) throws Exception;
    public CartResponse deleteItem(String email, String productCode) throws Exception;
}
