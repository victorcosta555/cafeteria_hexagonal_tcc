package com.example.coffeeshop;

import com.example.coffeeshop.application.usecases.CoffeMachine;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CoffeeshopInfrastructureApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CoffeMachine coffeeMachine;

    @Test
    void processNewOrder() throws Exception {
        var orderId = placeOrder();
        payOrder(orderId);
        prepareOrder(orderId);
        readReceipt(orderId);
        takeOrder(orderId);
    }

    @Test
    void cancelOrderBeforePayment() throws Exception {
        var orderId = placeOrder();
        cancelOrder(orderId);
    }

    private UUID placeOrder() throws Exception {
        var location = mockMvc.perform(post("/api/v1/order")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                        {
                            "location": "IN_STORE",
                            "items": [{
                                "drink": "LATTE",
                                "quantity": 1,
                                "milk": "WHOLE",
                                "size": "LARGE"
                            }]
                        }
                        """))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getHeader(HttpHeaders.LOCATION);

        return location == null ? null : UUID.fromString(location.substring(location.lastIndexOf("/") + 1));
    }

    private void payOrder(UUID orderId) throws Exception {
        mockMvc.perform(put("/api/v1/payment/{id}", orderId)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                        {
                            "cardHolderName": "Michael Faraday",
                            "cardNumber": "11223344",
                            "expiryMonth": 12,
                            "expiryYear": 2023
                        }
                        """))
                .andExpect(status().isOk());
    }

    private void prepareOrder(UUID orderId) {
        coffeeMachine.startPreparingOrder(orderId);
        coffeeMachine.finishPreparingOrder(orderId);
    }

    private void readReceipt(UUID orderId) throws Exception {
        mockMvc.perform(get("/api/v1/receipt/{id}", orderId))
                .andExpect(status().isOk());
    }

    private void takeOrder(UUID orderId) throws Exception {
        mockMvc.perform(delete("/api/v1/receipt/{id}", orderId))
                .andExpect(status().isOk());
    }

    private void cancelOrder(UUID orderId) throws Exception {
        mockMvc.perform(delete("/api/v1/order/{id}", orderId))
                .andExpect(status().isNoContent());
    }
}
