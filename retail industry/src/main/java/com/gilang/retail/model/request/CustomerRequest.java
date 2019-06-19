package com.gilang.retail.model.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@Getter
@NotNull
public class CustomerRequest {

    private int custId;
    @Size(min = 3, message = "must have at least 3 character")
    private String custName;
    private int custWallet;
}
