package com.gilang.retail.model.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@Getter
@NotNull
public class ItemRequest {
    @Size(min=1, message = "Id should have at least 1 digit")
    private String itemId;
    @Size(min=2, message = "Id should have at least 2 character")
    private String itemName;
    private int itemPrice;

}

