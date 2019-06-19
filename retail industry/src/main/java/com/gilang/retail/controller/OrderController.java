package com.gilang.retail.controller;

import com.gilang.retail.exception.DataNotFoundException;
import com.gilang.retail.model.request.OrderRequest;
import com.gilang.retail.model.response.OrderResponse;
import com.gilang.retail.model.response.OrderTotalResponse;
import com.gilang.retail.service.OrderService;
import com.gilang.retail.service.OrderTotalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    OrderTotalService orderTotalService;

    @GetMapping("/show/{customerId}")
    public ResponseEntity showOrder(@PathVariable("customerId") String customerId){
        if (customerId == null){
            throw new DataNotFoundException();
        } else{
            OrderTotalResponse orders = orderTotalService.showOrder(customerId);
            return new ResponseEntity<>(orders, HttpStatus.OK);
        }
    }

    @PostMapping("/create")
    public ResponseEntity createOrderItem(@RequestBody OrderRequest orderRequest){
        OrderResponse orderValue = orderService.createOrderItem(orderRequest);
        if (orderValue == null){
            throw new DataNotFoundException();
        } else{
            return new ResponseEntity<>(orderValue, HttpStatus.OK);
        }
    }
}
