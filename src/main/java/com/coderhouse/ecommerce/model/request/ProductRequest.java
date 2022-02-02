package com.coderhouse.ecommerce.model.request;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {

    @NotBlank(message = "code can't be blank")
    private String code;
    @NotBlank(message = "name can't be blank")
    private String name;
    private String description;
    private Double price;
    @NotBlank(message = "category can't be blank")
    private String categoryCode;
}
