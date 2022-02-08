package com.coderhouse.ecommerce.model.request;

import com.coderhouse.ecommerce.model.Item;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {

    @NotBlank(message = "email can't be blank")
    @Email(message = "email address is invalid")
    private String email;
    @Valid
    private Item item;
}
