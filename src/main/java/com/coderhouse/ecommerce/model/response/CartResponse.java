package com.coderhouse.ecommerce.model.response;

import com.coderhouse.ecommerce.model.request.Item;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CartResponse {

    private String email;
    private List<Item> products;
    private String shippingAddress;
    private LocalDateTime modificationDate;
}
