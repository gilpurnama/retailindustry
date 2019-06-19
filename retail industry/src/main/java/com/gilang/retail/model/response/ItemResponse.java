package com.gilang.retail.model.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Size;

@Setter
@Getter
@ToString
public class ItemResponse {
    @Size(min=1, message = "Id should have at least 1 digit")
    private String itemId;
    @Size(min=2, message = "Id should have at least 2 character")
    private String itemName;
    private int itemPrice;

    @Override
    public String toString() {
        return "Item{" +
                "id=" + itemId +
                ", itemName='" + itemName + '\'' +
                ", itemPrice='" + itemPrice + '\'' +
                '}';
    }
}

