package com.gilang.retail;

import com.gilang.retail.controller.CustomerController;
import com.gilang.retail.controller.ItemController;
import com.gilang.retail.controller.OrderController;
import com.gilang.retail.exception.DataNotFoundException;
import com.gilang.retail.model.request.CustomerRequest;
import com.gilang.retail.model.request.ItemRequest;
import com.gilang.retail.model.request.OrderRequest;
import com.gilang.retail.model.response.CustomerResponse;
import com.gilang.retail.model.response.ItemResponse;
import com.gilang.retail.service.OrderTotalService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ControllerTest {

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Autowired
    OrderTotalService orderTotalService;

    @Autowired
    ItemController itemController;

    @Autowired
    CustomerController customerController;

    @Autowired
    OrderController orderController;

    @Test
    public void createCustomer(){
        CustomerRequest custRequest = new CustomerRequest();
        custRequest.setCustId(9);
        custRequest.setCustName("Azka");
        custRequest.setCustWallet(500000);

        ResponseEntity createCustomer = customerController.createCustomer(custRequest);
        assertThat(createCustomer.getStatusCode().is2xxSuccessful()).isEqualTo(true);
    }

    @Test
    public void createItem(){
        ItemRequest itemReq = new ItemRequest();

        itemReq.setItemId("9");
        itemReq.setItemName("Jaket");
        itemReq.setItemPrice(150000);

        ResponseEntity createItem = itemController.createItem(itemReq);
        assertThat(createItem.getStatusCode().is2xxSuccessful()).isEqualTo(true);

    }

    @Test(expected = DataNotFoundException.class)
    public void itemNotFound(){
//        createTest();

        String itemTest = "5";
        ResponseEntity getItem = itemController.getById(itemTest);
        assertThat(getItem.getStatusCode().is2xxSuccessful()).isEqualTo(true);
    }

    @Test
    public void itemFound(){
//        createTest();

        String itemTest = "2";
        ResponseEntity getItem = itemController.getById(itemTest);
        log.info(getItem.toString());
    }

    @Test(expected = DataNotFoundException.class)
    public void custNotFound(){
//        createTest();

        String custId = "5";

        ResponseEntity getCust = customerController.showCustomerById(custId);
        log.info(getCust.toString());
    }

    @Test()
    public void cusFound(){
//        createTest();

        String custId = "1";

        ResponseEntity getCust = customerController.showCustomerById(custId);
        assertThat(getCust.getStatusCode().is2xxSuccessful()).isEqualTo(true);
    }

    @Test(expected = DataNotFoundException.class)
    public void itemUpdateNotFound(){
//        createTest();

        ItemResponse itemResp = new ItemResponse();
        String itemTest = "5";
        ResponseEntity getItem = itemController.updateItem(itemTest, itemResp);
        log.info(getItem.toString());
    }

    @Test(expected = DataNotFoundException.class)
    public void customerUpdateNotFound(){
//        createTest();

        CustomerResponse custResp = new CustomerResponse();
        String custId = "5";
        ResponseEntity getItem = customerController.updateCustomerWallet(custId, custResp);
        log.info(getItem.toString());
    }

    @Test
    public void deleteItemNotFound(){
        exceptionRule.expect(DataNotFoundException.class);
        exceptionRule.expectMessage("Item Not Found");

        String itemTest = "5";

        ResponseEntity deleteItem = itemController.deleteItem(itemTest);

    }

    @Test
    public void deleteItemFound(){
        String itemTest = "1";

        ResponseEntity deleteItem = itemController.deleteItem(itemTest);
        assertThat(deleteItem.getStatusCode().is2xxSuccessful()).isEqualTo(true);

    }

    @Test(expected = DataNotFoundException.class)
    public void deleteCustomerNotFound(){

        String itemTest = "5";

        ResponseEntity deleteCustomer = customerController.deleteCustomer(itemTest);
    }

    @Test
    public void deleteCustomerFound(){

        String itemTest = "1";

        ResponseEntity deleteCustomer = customerController.deleteCustomer(itemTest);
        assertThat(deleteCustomer.getStatusCode().is2xxSuccessful()).isEqualTo(true);
    }

    @Test(expected = DataNotFoundException.class)
    public void createOrderFailed(){

        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setCustId(123);
        orderRequest.setOrderId(1);
        orderRequest.setTotalOrderItem(5);
        ResponseEntity getOrder = orderController.createOrderItem(orderRequest);
        log.info(getOrder.toString());
    }

    @Test
    public void createOrder(){

        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setCustId(2);
        orderRequest.setOrderId(2);
        orderRequest.setTotalOrderItem(1);
        ResponseEntity getOrder = orderController.createOrderItem(orderRequest);
        assertThat(getOrder.getStatusCode().is2xxSuccessful()).isEqualTo(true);
    }

    @Test(expected = NullPointerException.class)
    public void showOrderFailed(){

        String custId = "5";
        ResponseEntity getOrder = orderController.showOrder(custId);
        log.info(getOrder.toString());
    }

    public void showOrder(){
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setCustId(2);
        orderRequest.setOrderId(1);
        orderRequest.setTotalOrderItem(1);
        ResponseEntity createOrder = orderController.createOrderItem(orderRequest);
        String custId = "2";
        ResponseEntity getOrder = orderController.showOrder(custId);
        assertThat(getOrder.getStatusCode().is2xxSuccessful()).isEqualTo(true);
    }

    @Test
    public void showAllItem(){

        ResponseEntity showItem = itemController.showAllItem();
        assertThat(showItem.toString()).contains("Celana");
    }

    @Test(expected = DataNotFoundException.class)
    public void createOrderFailedCustomer(){

        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setCustId(100);
        orderRequest.setOrderId(2);
        orderRequest.setTotalOrderItem(1);
        ResponseEntity getOrder = orderController.createOrderItem(orderRequest);
    }

    @Test(expected = DataNotFoundException.class)
    public void createOrderFailedWallet(){

        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setCustId(1);
        orderRequest.setOrderId(2);
        orderRequest.setTotalOrderItem(1000);
        ResponseEntity getOrder = orderController.createOrderItem(orderRequest);
    }
}
