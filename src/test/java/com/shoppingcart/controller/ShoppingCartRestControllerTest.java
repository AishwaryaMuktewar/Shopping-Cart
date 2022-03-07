package com.shoppingcart.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shoppingcart.dto.Cart;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ShoppingCartRestControllerTest {
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    MockMvc mockMvc;

    @Test
    public void addProductToCartTest() throws Exception {
        String url = "/add-to-kart";

        Cart cart = new Cart();
        cart.setId(101L);
        cart.setUserId("123");
        cart.setProductId(10L);
        cart.setProductName("JBL Bluetooth");
        cart.setProductPrice(1000.00);
        cart.setProductQuantity(1);
        cart.setProductDescription("good");
        Date date = new Date();
        date.setTime(2022-02-03);
        cart.setProductPurchasedDate(date);
        MvcResult result = mockMvc.perform(post(url).contentType("application/json")
                        .content(objectMapper.writeValueAsString(cart))
                        .with(csrf()))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();

        String jsonResponse = result.getResponse().getContentAsString();
        Cart cartObject = objectMapper.readValue(jsonResponse, Cart.class);

       assertThat(cartObject).isNotNull();

    }
}