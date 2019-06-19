package com.gilang.retail.model.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Size;

@Setter
@Getter
@ToString
public class CustomerResponse {

    private Integer custId;
    @Size(min = 3, message = "must have at least 3 character")
    private String custName;
    private int custWallet;
}
