package com.coderhouse.ecommerce.model.document;

import com.coderhouse.ecommerce.model.Item;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("cart")
public class CartDocument {

    @Id
    private String id;
    private String email;
    private List<Item> products = new ArrayList<>();
    private String shippingDirection;
    private LocalDateTime modificationDate;

}
