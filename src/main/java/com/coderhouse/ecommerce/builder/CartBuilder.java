package com.coderhouse.ecommerce.builder;

import com.coderhouse.ecommerce.model.document.CartDocument;
import com.coderhouse.ecommerce.model.document.CategoryDocument;
import com.coderhouse.ecommerce.model.request.CartRequest;
import com.coderhouse.ecommerce.model.request.CategoryRequest;
import com.coderhouse.ecommerce.model.response.CartResponse;
import com.coderhouse.ecommerce.model.response.CategoryResponse;
import com.coderhouse.ecommerce.repository.CartRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CartBuilder {

    public static CartDocument requestToDocument(CartRequest request) {
        return CartDocument.builder()
                .email(request.getEmail())
                .modificationDate(LocalDateTime.now())
                .shippingDirection(request.getShippingDirection())
                .build();
    }

    public static CartResponse documentToResponse(CartDocument document) {
        return CartResponse.builder()
                .email(document.getEmail())
                .products(document.getProducts())
                .modificationDate(document.getModificationDate())
                .shippingDirection(document.getShippingDirection())
                .build();
    }

    public static List<CartResponse> listDocumentsToListResponse(List<CartDocument> documents) {
        var listResponse = new ArrayList<CartResponse>();
        documents.forEach(document -> listResponse.add(documentToResponse(document)));
        return listResponse;
    }
}
