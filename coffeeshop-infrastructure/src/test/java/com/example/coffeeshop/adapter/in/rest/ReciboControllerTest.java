package com.example.coffeeshop.adapter.in.rest;

import com.example.coffeeshop.application.ports.out.Pedidos;
import com.example.coffeeshop.application.ports.out.Pagamentos;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static com.example.coffeeshop.application.textFitures.order.PedidoTestFactory.aReadyOrder;
import static com.example.coffeeshop.application.textFitures.order.PedidoTestFactory.anOrder;
import static com.example.coffeeshop.application.textFitures.payment.PagamentosTestFactory.aPaymentForOrder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RestResourceTest
public class ReciboControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Pedidos pedidos;

    @Autowired
    private Pagamentos pagamentos;

    @Test
    void readReceipt() throws Exception {
        var order = pedidos.savePedido(anOrder());
        pagamentos.save(aPaymentForOrder(order));

        mockMvc.perform(get("/api/v1/receipt/{id}", order.getId()))
                .andExpect(status().isOk());
    }

    @Test
    void completeOrder() throws Exception {
        var order = pedidos.savePedido(aReadyOrder());

        mockMvc.perform(delete("/api/v1/receipt/{id}", order.getId()))
                .andExpect(status().isOk());
    }
}
