package com.example.cafeteria_infra.adapter.entrada.rest;

import com.example.cafeteria.aplicacao.portas.saida.Pedidos;
import com.example.cafeteria.aplicacao.portas.saida.Pagamentos;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static com.example.cafeteria.aplicacao.textFitures.order.PedidoTestFactory.umPedidoPronto;
import static com.example.cafeteria.aplicacao.textFitures.order.PedidoTestFactory.umPedido;
import static com.example.cafeteria.aplicacao.textFitures.payment.PagamentosTestFactory.umPagametoParaPedido;
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
        var order = pedidos.savePedido(umPedido());
        pagamentos.save(umPagametoParaPedido(order));

        mockMvc.perform(get("/api/v1/receipt/{id}", order.getId()))
                .andExpect(status().isOk());
    }

    @Test
    void completeOrder() throws Exception {
        var order = pedidos.savePedido(umPedidoPronto());

        mockMvc.perform(delete("/api/v1/receipt/{id}", order.getId()))
                .andExpect(status().isOk());
    }
}
