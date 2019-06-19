package com.gilang.retail.service;

import com.gilang.retail.exception.DataNotFoundException;
import com.gilang.retail.model.request.OrderRequest;
import com.gilang.retail.model.response.CustomerResponse;
import com.gilang.retail.model.response.ItemResponse;
import com.gilang.retail.model.response.OrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.logging.Logger;

@Slf4j
@Service
public class OrderService {

    @Autowired
    private ItemService itemService;

    @Autowired
    private CustomerService customerService;

    OrderResponse orderResponse;

    public static HashMap<String, OrderResponse> orders = new HashMap<String, OrderResponse>();


    public static HashMap<String, OrderResponse> getHash() {
        return orders;
    }

    public OrderResponse createOrderItem(OrderRequest orderRequest){

        orderRequest.setCustId(orderRequest.getCustId());

        String custId = Integer.toString(orderRequest.getCustId());

        CustomerResponse customerOrder = customerService.showCustomerById(custId);


        if (customerOrder == null){
            throw new DataNotFoundException("Customer is not valid!");
        }

        orderResponse = new OrderResponse();

        orderResponse.setOrderId(orderRequest.getOrderId());
        String orderId = Integer.toString(orderResponse.getOrderId());

        ItemResponse itemResName = itemService.getById(orderId);

        orderResponse.setItemOrderName(itemResName.getItemName());

        orderResponse.setTotalOrderItem(orderRequest.getTotalOrderItem());


        ItemResponse itemSerTemp = itemService.getById(orderId);

        int totalOrder = orderResponse.getTotalOrderItem();
        int orderPrice = itemSerTemp.getItemPrice();
        int totalOrderPrice = orderPrice * totalOrder;
        orderResponse.setTotalOrderPrice(totalOrderPrice);
        int walletCustomer = customerOrder.getCustWallet();
        if (walletCustomer < totalOrderPrice) {
            throw new DataNotFoundException("Wallet is not enough");
        }

        orders.put(orderId, orderResponse);
        log.info(orders.toString());
        return orderResponse;
    }

}
