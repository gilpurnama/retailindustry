package com.gilang.retail.service;

import com.gilang.retail.exception.DataNotFoundException;
import com.gilang.retail.model.response.CustomerResponse;
import com.gilang.retail.model.response.ItemResponse;
import com.gilang.retail.model.response.OrderResponse;
import com.gilang.retail.model.response.OrderTotalResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class OrderTotalService {

    @Autowired
    private CustomerService customerService;

    OrderTotalResponse orderTotalResponse;

    OrderService x = new OrderService();
    HashMap<String, OrderResponse> orders = x.getHash();


    public OrderTotalResponse showOrder(String customerId) {

        CustomerResponse customerOrder = customerService.showCustomerById(customerId);

        OrderTotalResponse orderTotal = new OrderTotalResponse();

        orderTotal.setCustomerId(customerOrder.getCustId());
        orderTotal.setCustomerName(customerOrder.getCustName());

        log.info(orders.toString());
        orderTotal.setAllOrder(orders.values());
        orderTotal.setTotalOrder(totalOrder().getTotalOrder());
        orderTotal.setTotalPrice(totalOrder().getTotalPrice());

        Double totalPrice = (orderTotal.getTotalPrice() * 0.1) + orderTotal.getTotalPrice();

        orderTotal.setTotalPrice(totalPrice);

        Double customerWallet = new Double(customerOrder.getCustWallet());

        orderTotal.setCustomerWallet(customerWallet);
        Double wallet = orderTotal.getCustomerWallet();
        if (wallet >= totalPrice){
            Double newWallet = wallet - totalPrice;
            orderTotal.setCustomerWalletLeft(newWallet);
            int custWalletChange = newWallet.intValue();
            customerOrder.setCustWallet(custWalletChange);
        } else {
            throw new DataNotFoundException("Wallet is not enough");
        }

        return orderTotal;
    }

    private OrderTotalResponse totalOrder(){

        //orderResponse = new OrderResponse();
        orderTotalResponse = new OrderTotalResponse();

        int totalItem = 0;
        Double totalPrice = 0.0;

        HashMap<String, OrderResponse> map = orders;

        for (Map.Entry<String, OrderResponse> me: map.entrySet()){

            totalItem += me.getValue().getTotalOrderItem();
            totalPrice += me.getValue().getTotalOrderPrice();

            orderTotalResponse.setTotalPrice(totalPrice);
            orderTotalResponse.setTotalOrder(totalItem);
        }

        return orderTotalResponse;
    }
}
