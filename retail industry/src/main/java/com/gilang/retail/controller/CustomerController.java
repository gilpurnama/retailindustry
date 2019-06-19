package com.gilang.retail.controller;

import com.gilang.retail.exception.DataNotFoundException;
import com.gilang.retail.model.request.CustomerRequest;
import com.gilang.retail.model.response.CustomerResponse;
import com.gilang.retail.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/{custId}")
    public ResponseEntity showCustomerById(@PathVariable("custId") String custId){
        CustomerResponse custValue = customerService.showCustomerById(custId);
        if (custValue != null){
            return new ResponseEntity<>(custValue, HttpStatus.OK);
        } else{
            throw new DataNotFoundException();
        }
    }

    @PostMapping("/create")
    public ResponseEntity createCustomer(@Valid @RequestBody CustomerRequest customerRequest){
        CustomerResponse custValue = customerService.createCustomer(customerRequest);
        return new ResponseEntity<>(custValue, HttpStatus.CREATED);
    }

    @PatchMapping("/update/{custId}")
    public ResponseEntity updateCustomerWallet(@PathVariable("custId") String custId, @RequestBody CustomerResponse customerResponse){
        CustomerResponse custValue = customerService.updateCustomerWallet(custId, customerResponse);
        if (custValue != null) {
            return new ResponseEntity<>(custValue, HttpStatus.OK);
        } else {
            throw new DataNotFoundException("Customer Not Found");
        }
    }

    @DeleteMapping("/delete/{custId}")
    public ResponseEntity deleteCustomer(@PathVariable("custId") String custId){
        CustomerResponse custValue = customerService.deleteCustomer(custId);
        if (custValue != null ){
            return new ResponseEntity(custValue, HttpStatus.OK);
        } else {
            throw new DataNotFoundException("Customer Not Found");
        }
    }

}
