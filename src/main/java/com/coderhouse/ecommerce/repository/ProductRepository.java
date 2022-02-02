package com.coderhouse.ecommerce.repository;

import com.coderhouse.ecommerce.model.document.CategoryDocument;
import com.coderhouse.ecommerce.model.document.ProductDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<ProductDocument, String> {
    ProductDocument findByCode(String code);
    boolean existsByCode(String code);
}
