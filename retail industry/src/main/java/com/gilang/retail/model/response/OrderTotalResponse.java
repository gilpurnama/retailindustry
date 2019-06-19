package com.gilang.retail.model.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@Setter
@Getter
public class OrderTotalResponse {
    private int customerId;
    private String customerName;
    private Collection allOrder;
    private int totalOrder;
    private Double totalPrice;
    private Double customerWallet;
    private Double customerWalletLeft;
}
