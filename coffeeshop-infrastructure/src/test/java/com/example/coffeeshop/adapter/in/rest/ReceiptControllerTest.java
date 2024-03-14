package com.example.coffeeshop.adapter.in.rest;

import com.example.coffeeshop.application.ports.out.Orders;
import com.example.coffeeshop.application.ports.out.Payments;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static com.example.coffeeshop.application.textFitures.order.OrderTestFactory.aReadyOrder;
import static com.example.coffeeshop.application.textFitures.order.OrderTestFactory.anOrder;
import static com.example.coffeeshop.application.textFitures.payment.PaymentTestFactory.aPaymentForOrder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RestResourceTest
public class ReceiptControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Orders orders;

    @Autowired
    private Payments payments;

    @Test
    void readReceipt() throws Exception {
        var order = orders.saveOrder(anOrder());
        payments.save(aPaymentForOrder(order));

        mockMvc.perform(get("/api/v1/receipt/{id}", order.getId()))
                .andExpect(status().isOk());
    }

    @Test
    void completeOrder() throws Exception {
        var order = orders.saveOrder(aReadyOrder());

        mockMvc.perform(delete("/api/v1/receipt/{id}", order.getId()))
                .andExpect(status().isOk());
    }
}
