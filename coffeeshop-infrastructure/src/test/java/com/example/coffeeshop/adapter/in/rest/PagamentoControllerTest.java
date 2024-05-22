package com.example.coffeeshop.adapter.in.rest;

import com.example.coffeeshop.application.ports.out.Pedidos;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.example.coffeeshop.application.textFitures.order.PedidoTestFactory.anOrder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RestResourceTest
public class PagamentoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Pedidos pedidos;

    @Test
    void payOrder() throws Exception {
        var order = pedidos.savePedido(anOrder());

        mockMvc.perform(put("/api/v1/payment/{id}", order.getId())
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
}
