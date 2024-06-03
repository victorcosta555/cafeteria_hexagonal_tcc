package com.example.cafeteria_infra.adapter.entrada.rest;

import com.example.cafeteria.aplicacao.portas.saida.Pedidos;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.example.cafeteria.aplicacao.textFitures.order.PedidoTestFactory.umPedido;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RestResourceTest
public class PedidoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Pedidos pedidos;

    private final String orderJson = """
                {
                    "localConsumoPedido": "LOJA",
                    "items": [
                        {
                            "bebida": "LATTE",
                            "quantidade": 1,
                            "tipoLeite": "INTEGRAL",
                            "tamanhoBebida": "GRANDE"
                        }
                    ]
                }
            """;

    @Test
    void createOrder() throws Exception {
        mockMvc.perform(post("/api/v1/order")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(orderJson))
                .andExpect(status().isCreated());
    }

    @Test
    void updateOrder() throws Exception {
        var order = pedidos.savePedido(umPedido());

        mockMvc.perform(put("/api/v1/order/{id}", order.getId())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(orderJson))
                .andExpect(status().isOk());
    }

    @Test
    void cancelOrder() throws Exception {
        var order = pedidos.savePedido(umPedido());

        mockMvc.perform(delete("/api/v1/order/{id}", order.getId()))
                .andExpect(status().isNoContent());
    }
}
