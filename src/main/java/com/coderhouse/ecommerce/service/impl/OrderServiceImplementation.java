package com.coderhouse.ecommerce.service.impl;

import com.coderhouse.ecommerce.builder.OrderBuilder;
import com.coderhouse.ecommerce.exception.CartEmptyException;
import com.coderhouse.ecommerce.exception.CartNotFoundException;
import com.coderhouse.ecommerce.model.request.OrderRequest;
import com.coderhouse.ecommerce.model.response.OrderResponse;
import com.coderhouse.ecommerce.repository.CartRepository;
import com.coderhouse.ecommerce.repository.OrderRepository;
import com.coderhouse.ecommerce.service.OrderService;
import com.coderhouse.ecommerce.util.CheckExist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImplementation implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private OrderBuilder orderBuilder;
    @Autowired
    private CheckExist checkExist;


    @Override
    public OrderResponse create(OrderRequest request) throws Exception {
        var email = request.getEmail();
        if(!checkExist.cart(email)) {
            throw new CartNotFoundException();
        }

        if(cartRepository.findByEmail(email).getProducts().isEmpty()) {
            throw new CartEmptyException();
        }

        var cartDocument = cartRepository.findByEmail(email);
        //cuantas ordenes se encuentran generadas
        var count = orderRepository.count();
        var orderDocument = orderBuilder.cartDocumentToDocument(cartDocument, count);

        //vacío el carrito
        cartRepository.delete(cartDocument);

        return orderBuilder.documentToResponse(orderRepository.save(orderDocument));
    }

    @Override
    public List<OrderResponse> getAll() {
        return orderBuilder.listDocumentsToListResponse(orderRepository.findAll());
    }
}
