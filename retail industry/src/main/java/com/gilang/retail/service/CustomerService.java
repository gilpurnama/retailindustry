package com.gilang.retail.service;

import com.gilang.retail.model.request.CustomerRequest;
import com.gilang.retail.model.response.CustomerResponse;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class CustomerService{

    CustomerResponse customerResponse;
    HashMap<String, CustomerResponse> customers;

    public CustomerService(){
        CustomerResponse customerResponse1 = new CustomerResponse();

        customerResponse1.setCustId(1);
        String customerId = Integer.toString(customerResponse1.getCustId());

        customerResponse1.setCustName("Gilang");
        customerResponse1.setCustWallet(500000);

        if (customers == null){
            customers = new HashMap<>();
        }
        customers.put(customerId, customerResponse1);

        CustomerResponse customerResponse2 = new CustomerResponse();

        customerResponse2.setCustId(2);
        String customerId2 = Integer.toString(customerResponse2.getCustId());

        customerResponse2.setCustName("Purnama");
        customerResponse2.setCustWallet(1000000);

        customers.put(customerId2, customerResponse2);

        CustomerResponse customerResponse3 = new CustomerResponse();

        customerResponse3.setCustId(3);
        String customerId3 = Integer.toString(customerResponse3.getCustId());

        customerResponse3.setCustName("test");
        customerResponse3.setCustWallet(50000);

        customers.put(customerId3, customerResponse3);
    }

    public CustomerResponse showCustomerById(String customerId){
        return customers.get(customerId);
    }

    public CustomerResponse createCustomer(CustomerRequest customerRequest){
        customerResponse = new CustomerResponse();

        customerResponse.setCustId(customerRequest.getCustId());
        String customerId = Integer.toString(customerResponse.getCustId());

        customerResponse.setCustName(customerRequest.getCustName());
        customerResponse.setCustWallet(customerRequest.getCustWallet());

        customers.put(customerId, customerResponse);

        return customerResponse;
    }

    public CustomerResponse updateCustomerWallet(String custId, CustomerResponse customerResponse){
        if (customers.containsKey(custId)){
            CustomerResponse customerTemp = customers.get(custId);

            customerTemp.setCustWallet(customerResponse.getCustWallet());

            customers.put(custId, customerTemp);

        }

        return customers.get(custId);
    }

    public CustomerResponse deleteCustomer(String custId){
        return customers.remove(custId);
    }
}
