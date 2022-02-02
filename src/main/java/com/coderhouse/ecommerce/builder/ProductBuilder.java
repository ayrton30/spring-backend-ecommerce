package com.coderhouse.ecommerce.builder;


import com.coderhouse.ecommerce.model.document.ProductDocument;
import com.coderhouse.ecommerce.model.request.ProductRequest;
import com.coderhouse.ecommerce.model.response.ProductResponse;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProductBuilder {

    public static ProductDocument requestToDocument(ProductRequest request) {
        return ProductDocument.builder()
                .code(request.getCode())
                .name(request.getName())
                .description(request.getDescription())
                .categoryCode(request.getCategoryCode())
                .price(request.getPrice())
                .modificationDate(LocalDateTime.now())
                .build();
    }

    public static ProductResponse documentToResponse(ProductDocument document) {
        return ProductResponse.builder()
                .code(document.getCode())
                .name(document.getName())
                .description(document.getDescription())
                .categoryCode(document.getCategoryCode())
                .price(document.getPrice())
                .modificationDate(document.getModificationDate())
                .status(true)
                .build();
    }

    public static List<ProductResponse> listDocumentsToListResponse(List<ProductDocument> documents) {
        var listResponse = new ArrayList<ProductResponse>();
        documents.forEach(document -> listResponse.add(documentToResponse(document)));
        return listResponse;
    }
}
