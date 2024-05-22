package com.example.cafeteria_infra;

import com.example.cafeteria.aplicacao.usecases.MaquinaCafe;
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
class AplicacaoCafeteriaTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MaquinaCafe coffeeMachine;

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
        var localConsumoPedido = mockMvc.perform(post("/api/v1/order")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                        {
                            "localConsumoPedido": "LOJA",
                            "items": [{
                                "bebida": "LATTE",
                                "quantidade": 1,
                                "tipoLeite": "INTEGRAL",
                                "tamanhoBebida": "GRANDE"
                            }]
                        }
                        """))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getHeader(HttpHeaders.LOCATION);

        return localConsumoPedido == null ? null : UUID.fromString(localConsumoPedido.substring(localConsumoPedido.lastIndexOf("/") + 1));
    }

    private void payOrder(UUID orderId) throws Exception {
        mockMvc.perform(put("/api/v1/payment/{id}", orderId)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                        {
                            "nomeUsuarioCartao": "Victor Costa",
                            "numeroCartao": "11223344",
                            "mesValidade": 12,
                            "anoValidade": 2023
                        }
                        """))
                .andExpect(status().isOk());
    }

    private void prepareOrder(UUID orderId) {
        coffeeMachine.iniciarPreparacaoBebida(orderId);
        coffeeMachine.finalizarPreparacaoBebida(orderId);
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
