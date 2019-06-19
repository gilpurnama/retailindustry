package com.gilang.retail;

import com.gilang.retail.controller.CustomerController;
import com.gilang.retail.controller.ItemController;
import com.gilang.retail.controller.OrderController;
import com.gilang.retail.exception.AppExceptionHandler;
import com.gilang.retail.exception.DataNotFoundException;
import com.gilang.retail.model.request.CustomerRequest;
import com.gilang.retail.model.request.ItemRequest;
import com.gilang.retail.model.request.OrderRequest;
import com.gilang.retail.model.response.CustomerResponse;
import com.gilang.retail.model.response.ItemResponse;
import com.gilang.retail.model.response.OrderResponse;
import com.gilang.retail.model.response.OrderTotalResponse;
import com.gilang.retail.service.CustomerService;
import com.gilang.retail.service.ItemService;
import com.gilang.retail.service.OrderService;
import com.gilang.retail.service.OrderTotalService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RetailAppApplicationTests {

    @Autowired
    CustomerService customerService;

    @Autowired
    ItemService itemService;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderTotalService orderTotalService;

    @Autowired
    ItemController itemController;

    @Autowired
    CustomerController customerController;

    @Autowired
    OrderController orderController;

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    OrderResponse orderResponse;
    HashMap<String, OrderResponse> orderMap;

    @Test
    public void createCustomerTest() {

        log.info("CREATE TEST");
        log.info("");
        log.info("CREATE CUSTOMER");

        CustomerRequest testCustomer = new CustomerRequest();
        testCustomer.setCustId(11);
        testCustomer.setCustName("Testing");
        testCustomer.setCustWallet(1000000);

        log.info("-------- Parameter that we POST --------");
        log.info("customer id = " + testCustomer.getCustId());
        log.info("customer name = " + testCustomer.getCustName());
        log.info("customer wallet = " + testCustomer.getCustWallet());

        CustomerResponse testCustRes = customerService.createCustomer(testCustomer);

        assertThat(testCustRes.getCustId()).isEqualTo(testCustomer.getCustId());
        assertThat(testCustRes.getCustName()).isEqualTo(testCustomer.getCustName());
        assertThat(testCustRes.getCustWallet()).isEqualTo(testCustomer.getCustWallet());

        log.info("----------- Posted Parameter -----------");
        log.info(testCustRes.toString());

    }

    @Test
    public void createItemTest(){

        log.info("");
        log.info("CREATE ITEM");

        ItemRequest itemTest = new ItemRequest();

        itemTest.setItemId("11");
        itemTest.setItemName("Celana panjang");
        itemTest.setItemPrice(50000);

        log.info("-------- Parameter that we POST --------");
        log.info("item id = " + itemTest.getItemId());
        log.info("item name = " + itemTest.getItemName());
        log.info("item price = " + itemTest.getItemPrice());

        ItemResponse itemTestRes = itemService.createItem(itemTest);

        assertThat(itemTestRes.getItemId()).isEqualTo(itemTest.getItemId());
        assertThat(itemTestRes.getItemName()).isEqualTo(itemTest.getItemName());
        assertThat(itemTestRes.getItemPrice()).isEqualTo(itemTest.getItemPrice());

        log.info("----------- Posted Parameter -----------");
        log.info(itemTestRes.toString());

    }

    @Test
    public void createOrderTest() {
        createItemTest();
        createCustomerTest();

        log.info("");
        log.info("CREATE ORDER");

        OrderRequest orderTest = new OrderRequest();

        orderTest.setCustId(11);
        orderTest.setOrderId(11);
        String itemIdTemp = Integer.toString(orderTest.getOrderId());
        orderTest.setTotalOrderItem(1);

        ItemResponse itemTest = itemService.getById(itemIdTemp);


        int totalItemPrice = orderTest.getTotalOrderItem() * itemTest.getItemPrice();

        orderResponse = new OrderResponse();

        OrderResponse orderTestRes = orderService.createOrderItem(orderTest);

        assertThat(orderTestRes.getOrderId()).isEqualTo(orderTest.getOrderId());
        assertThat(orderTestRes.getTotalOrderItem()).isEqualTo(orderTest.getTotalOrderItem());
        assertThat(orderTestRes.getTotalOrderPrice()).isEqualTo(totalItemPrice);

        orderResponse.setOrderId(orderTestRes.getOrderId());
        String orderId = Integer.toString(orderResponse.getOrderId());
        log.info(orderId);

        orderResponse.setItemOrderName(orderTestRes.getItemOrderName());
        String itemOrderName = orderResponse.getItemOrderName();
        log.info(itemOrderName);

        orderResponse.setTotalOrderPrice(orderTestRes.getTotalOrderPrice());
        String totalPrice = Integer.toString(orderResponse.getTotalOrderPrice());
        log.info(totalPrice);

        orderResponse.setTotalOrderItem(orderTestRes.getTotalOrderItem());
        String totalOrder = Integer.toString(orderResponse.getTotalOrderItem());
        log.info(totalOrder);

        String orderItemId = Integer.toString(orderTestRes.getOrderId());
        log.info(orderItemId);

        if (orderMap == null) {
            orderMap = new HashMap<>();
        }

        orderMap.put(orderId, orderResponse);

        log.info(orderMap.toString());

        //OrderTotalResponse orderTotalResponse = orderTotalService.showOrder("11");

    }

    @Test
    public void getAllItemTest(){
        createItemTest();

        Collection getAllItem = itemService.showAllItem();
        assertThat(getAllItem.size()).isEqualTo(4);

    }

    @Test
    public void getItemTest(){
        createItemTest();

        String itemIdTes = "11";
        log.info("Get item id = " + itemIdTes);

        ItemResponse itemResTemp = itemService.getById(itemIdTes);

        assertThat(itemResTemp.getItemId()).isEqualTo("11");
        assertThat(itemResTemp.getItemName()).isEqualTo("Celana panjang");
        assertThat(itemResTemp.getItemPrice()).isEqualTo(50000);

    }

    @Test
    public void getCustomerTest(){
        createCustomerTest();

        String custIdTes = "11";
        log.info("Get customer id = " + custIdTes);

        CustomerResponse getCustTest = customerService.showCustomerById(custIdTes);

        assertThat(getCustTest.getCustId()).isEqualTo(11);
        assertThat(getCustTest.getCustName()).isEqualTo("Testing");
        assertThat(getCustTest.getCustWallet()).isEqualTo(1000000);

    }


    @Test
    public void getOrderTest(){
        createCustomerTest();
        createItemTest();
        createOrderTest();

        log.info("");
        log.info("GET TEST");
        log.info("GET TOTAL ORDER");

        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setCustId(11);
        String custId = Integer.toString(orderRequest.getCustId());
        orderRequest.setOrderId(11);
        orderRequest.setTotalOrderItem(1);

        CustomerResponse getCustTest = customerService.showCustomerById(custId);

        OrderTotalResponse getAllOrder = orderTotalService.showOrder(custId);

        getAllOrder.setCustomerId(getCustTest.getCustId());
        getAllOrder.setCustomerName(getCustTest.getCustName());
        Double custWallet = Double.valueOf(getCustTest.getCustWallet());
        getAllOrder.setCustomerWallet(custWallet);
        getAllOrder.setAllOrder(orderMap.values());

        int totalItem = 0;
        Double totalPrice = 0.0;
        HashMap<String, OrderResponse> map = orderMap;
        for (Map.Entry<String, OrderResponse> me: map.entrySet()){
            totalItem += me.getValue().getTotalOrderItem();
            totalPrice += me.getValue().getTotalOrderPrice();
        }

        getAllOrder.setTotalPrice(totalPrice);
        getAllOrder.setTotalOrder(totalItem);
        Double wallet = getAllOrder.getCustomerWallet();
        if (wallet >= totalPrice) {
            Double newWallet = wallet - totalPrice;
            getAllOrder.setCustomerWalletLeft(newWallet);
        }

        assertThat(getAllOrder.getCustomerId()).isEqualTo(getCustTest.getCustId());
        assertThat(getAllOrder.getCustomerName()).isEqualTo(getCustTest.getCustName());
        assertThat(getAllOrder.getAllOrder().size()).isEqualTo(1);
        assertThat(getAllOrder.getCustomerWallet()).isEqualTo(getCustTest.getCustWallet());
        assertThat(getAllOrder.getTotalPrice()).isEqualTo(totalPrice);
        assertThat(getAllOrder.getTotalOrder()).isEqualTo(totalItem);
        assertThat(getAllOrder.getCustomerWalletLeft()).isBetween(0.0, getAllOrder.getCustomerWallet());

    }

    @Test
    public void updateTest(){
        createCustomerTest();
        createItemTest();

        log.info("");
        log.info("UPDATE TEST");
        log.info("ITEM");

        String upItemId = "11";
        ItemResponse getItemRes = itemService.getById(upItemId);

        String itemid = getItemRes.getItemId();
        String itemName = getItemRes.getItemName();
        int itemPrice = getItemRes.getItemPrice();

        ItemResponse updateItemTes = itemService.updateItem(upItemId, getItemRes);

        updateItemTes.setItemName("Celana pendek");
        updateItemTes.setItemPrice(30000);

        log.info("----------- Posted Parameter -----------");
        log.info(updateItemTes.toString());

        log.info("----------- Compared Parameter -----------");
        log.info("id = " + itemid + ", itemName = " + itemName + ", itemPrice = "+ itemPrice);

        assertThat(updateItemTes.getItemId()).isEqualTo(itemid);
        assertThat(updateItemTes.getItemName()).isNotEqualTo(itemName);
        assertThat(updateItemTes.getItemPrice()).isNotEqualTo(itemPrice);

        ResponseEntity updateItemControllerTest = itemController.updateItem(upItemId, updateItemTes);
        assertThat(updateItemControllerTest.getStatusCode().is2xxSuccessful()).isEqualTo(true);

        log.info("CUSTOMER");

        String upCustId = "11";
        CustomerResponse getCusRes = customerService.showCustomerById(upCustId);

        int custId = getCusRes.getCustId();
        String custName = getCusRes.getCustName();
        int custWallet = getCusRes.getCustWallet();

        CustomerResponse updateCustTest = customerService.updateCustomerWallet(upCustId, getCusRes);

        updateCustTest.setCustName("Nama Test 2");
        updateCustTest.setCustWallet(12345567);

        log.info("----------- Posted Parameter -----------");
        log.info(updateCustTest.toString());

        log.info("----------- Compared Parameter -----------");
        log.info("id = " + custId + ", customer Name = " + custName + ", customer Wallet = "+ custWallet);

        assertThat(updateCustTest.getCustId()).isEqualTo(custId);
        assertThat(updateCustTest.getCustName()).isNotEqualTo(custName);
        assertThat(updateCustTest.getCustWallet()).isNotEqualTo(custWallet);
    }

    @Test
    public void updateItemTest(){
        createItemTest();

        log.info("");
        log.info("UPDATE TEST");
        log.info("ITEM");

        String upItemId = "11";
        ItemResponse getItemRes = new ItemResponse();

        getItemRes.setItemName("Celana pendek");
        getItemRes.setItemPrice(30000);

        ResponseEntity updateItemControllerTest = itemController.updateItem(upItemId, getItemRes);
        assertThat(updateItemControllerTest.getStatusCode().is2xxSuccessful()).isEqualTo(true);
    }

    @Test
    public void updateCustomerWalletTest(){
        createCustomerTest();

        log.info("CUSTOMER");

        String upCustId = "11";
        CustomerResponse getCusRes = new CustomerResponse();

        CustomerResponse updateCustTest = customerService.updateCustomerWallet(upCustId, getCusRes);

        updateCustTest.setCustWallet(12345567);

        ResponseEntity updateCustomerControllerTest = customerController.updateCustomerWallet(upCustId, updateCustTest);
        assertThat(updateCustomerControllerTest.getStatusCode().is2xxSuccessful()).isEqualTo(true);
    }

    @Test
    public void deleteTest(){
        //createTest();

        log.info("");
        log.info("DELETE TEST");
        log.info("ITEM");

        String itemId = "11";
        ItemResponse itemResponse = itemService.deleteItem(itemId);
        assertThat(itemService.getById(itemId)).isNull();

        log.info("");
        log.info("DELETE TEST");
        log.info("CUSTOMER");

        String customerId = "11";
        CustomerResponse customerResponse = customerService.deleteCustomer(customerId);
        assertThat(customerService.showCustomerById(customerId)).isNull();

    }

    @Test(expected = DataNotFoundException.class)
    public void createOrderFailed(){
        OrderRequest orderTest = new OrderRequest();

        orderTest.setCustId(100);
        orderTest.setOrderId(11);
        String itemIdTemp = Integer.toString(orderTest.getOrderId());
        orderTest.setTotalOrderItem(1);

        OrderResponse orderTestRes = orderService.createOrderItem(orderTest);

    }

    @Test(expected = DataNotFoundException.class)
    public void createOrderWallet(){
        OrderRequest orderTest = new OrderRequest();

        orderTest.setCustId(11);
        orderTest.setOrderId(11);
        String itemIdTemp = Integer.toString(orderTest.getOrderId());
        orderTest.setTotalOrderItem(1000);

        OrderResponse orderTestRes = orderService.createOrderItem(orderTest);

    }

    @Test
    public void mainTest(){
        RetailAppApplication.main(new String[] {});
    }
}
