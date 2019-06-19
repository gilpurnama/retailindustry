package com.gilang.retail.model.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class OrderResponse {
    private int orderId;
    private String itemOrderName;
    private int totalOrderItem;
    private int totalOrderPrice;
}
