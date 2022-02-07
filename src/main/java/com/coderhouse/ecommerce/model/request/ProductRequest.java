package com.coderhouse.ecommerce.model.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {

    @NotBlank(message = "code can't be blank")
    @Pattern(regexp = "^[P].*$", message = "code of product needs to start with 'P'")
    private String code;
    @NotBlank(message = "name can't be blank")
    private String name;
    private String description;
    private Double price;
    @NotBlank(message = "category can't be blank")
    private String categoryCode;
}
