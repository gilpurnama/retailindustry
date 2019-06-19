package com.gilang.retail.model.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@NotNull
public class OrderRequest {

    private int custId;
    private int orderId;
    private int totalOrderItem;
}
