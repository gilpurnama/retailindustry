package com.gilang.retail;

import com.gilang.retail.controller.ItemController;
import com.gilang.retail.controller.OrderController;
import com.gilang.retail.service.ItemService;
import com.gilang.retail.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ItemController.class)
@Slf4j
public class InvalidArgumentTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemService itemService;

    @Test
    public void createItem() throws Exception {
        MvcResult result = this.mockMvc.perform(post("/item/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                "        \"itemId\": \"4\",\n" +
                "        \"itemName\": \"T\",\n" +
                "        \"itemPrice\": \"20000\"\n" +
                "}"))
                .andExpect(status().is4xxClientError())
        .andDo(print()).andReturn();

        String content = result.getResponse().getContentAsString();
        log.info(content);

    }

    @Test
    public void getItem() throws Exception {
        MvcResult result = this.mockMvc.perform(get("/item/showitem/10")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andDo(print()).andReturn();

        String content = result.getResponse().getContentAsString();
        log.info(content);

    }

    @Test
    public void updateItem() throws Exception {
        MvcResult result = this.mockMvc.perform(patch("/item/update/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "        \"itemName\": \"T\",\n" +
                        "        \"itemPrice\": \"20000\"\n" +
                        "}"))
                .andExpect(status().is4xxClientError())
                .andDo(print()).andReturn();

        String content = result.getResponse().getContentAsString();
        log.info(content);

    }

//    @Test(expected = DataNotFoundException.class)
//    public void createOrderFailedCustomer(){
//
//        OrderRequest orderRequest = new OrderRequest();
//        orderRequest.setCustId(100);
//        orderRequest.setOrderId(2);
//        orderRequest.setTotalOrderItem(1);
//        ResponseEntity getOrder = orderController.createOrderItem(orderRequest);
//        assertThat(getOrder.getStatusCode().is2xxSuccessful()).isEqualTo(true);
//    }
}
