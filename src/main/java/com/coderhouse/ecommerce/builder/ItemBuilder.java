package com.coderhouse.ecommerce.builder;

import com.coderhouse.ecommerce.model.request.Item;
import com.coderhouse.ecommerce.model.response.ItemOrder;
import com.coderhouse.ecommerce.repository.CategoryRepository;
import com.coderhouse.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ItemBuilder {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public ItemOrder itemToItemOrder(Item item) {

        var productCode = item.getProductCode();
        var product = productRepository.findByCode(productCode);

        var categoryCode = product.getCategoryCode();
        var category = categoryRepository.findByCode(categoryCode);

        return ItemOrder.builder()
                .productName(product.getName())
                .productDescription(product.getDescription())
                .productPrice(product.getPrice())
                .categoryName(category.getName())
                .quantity(item.getQuantity())
                .build();
    }

    public List<ItemOrder> listToListResponse(List<Item> items) {
        var listResponse = new ArrayList<ItemOrder>();
        items.forEach(item -> listResponse.add(itemToItemOrder(item)));
        return listResponse;
    }


}
