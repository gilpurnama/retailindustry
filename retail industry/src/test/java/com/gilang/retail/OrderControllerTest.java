package com.gilang.retail;

import com.gilang.retail.controller.CustomerController;
import com.gilang.retail.controller.OrderController;
import com.gilang.retail.model.request.CustomerRequest;
import com.gilang.retail.model.request.ItemRequest;
import com.gilang.retail.model.response.CustomerResponse;
import com.gilang.retail.model.response.ItemResponse;
import com.gilang.retail.service.CustomerService;
import com.gilang.retail.service.ItemService;
import com.gilang.retail.service.OrderService;
import com.gilang.retail.service.OrderTotalService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.HashMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({OrderController.class, CustomerController.class})
@Slf4j
public class OrderControllerTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @MockBean
    private OrderTotalService orderTotalService;

    @MockBean
    private CustomerService customerService;

    @Autowired
    private CustomerService custService;

    @Autowired
    private CustomerController custController;

    @MockBean
    private ItemService itemService;

    @Autowired
    private ItemService itServe;

    CustomerResponse customerResponse;
    HashMap<String, CustomerResponse> customers;

    @Test
    public void createOrderFailed() throws Exception {

        CustomerResponse customerResponse1 = new CustomerResponse();

        customerResponse1.setCustId(1);
        String customerId = Integer.toString(customerResponse1.getCustId());

        customerResponse1.setCustName("Gilang");
        customerResponse1.setCustWallet(500000);

        if (customers == null){
            customers = new HashMap<>();
        }
        customers.put(customerId, customerResponse1);

        CustomerRequest testCustomer = new CustomerRequest();
        testCustomer.setCustId(2);
        testCustomer.setCustName("Testing");
        testCustomer.setCustWallet(2000);

        ResponseEntity addCustomer = custController.createCustomer(testCustomer);

        mockMvc.perform(post("/customer/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        " \"custId\": 3,\n" +
                        " \"custName\": \"Shalahuddin\",\n" +
                        " \"custWallet\": 10000\n" +
                        "}"))
                .andExpect(status().is2xxSuccessful())
                .andDo(print()).andReturn();

        log.info("----------------");
        log.info("----------------");
        log.info("----------------");
        log.info("----------------");
        log.info("----------------");

        mockMvc.perform(get("customer/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andReturn();

        log.info("----------------");
        log.info("----------------");
        log.info("----------------");
        log.info("----------------");
        log.info("----------------");

        MvcResult result = this.mockMvc.perform(post("/order/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\t\"custId\": 11,\n" +
                        "    \"orderId\": 11,\n" +
                        "    \"totalOrderItem\": 1\n" +
                        "}"))
                .andExpect(status().is4xxClientError())
                .andDo(print()).andReturn();

        String content = result.getResponse().getContentAsString();
        log.info(content);

    }
}
