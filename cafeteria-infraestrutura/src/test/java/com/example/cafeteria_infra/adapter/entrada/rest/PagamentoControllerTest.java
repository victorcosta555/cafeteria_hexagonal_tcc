package com.example.cafeteria_infra.adapter.entrada.rest;

import com.example.cafeteria.aplicacao.portas.saida.Pedidos;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.example.cafeteria.aplicacao.textFitures.order.PedidoTestFactory.umPedido;
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
        var order = pedidos.savePedido(umPedido());

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
