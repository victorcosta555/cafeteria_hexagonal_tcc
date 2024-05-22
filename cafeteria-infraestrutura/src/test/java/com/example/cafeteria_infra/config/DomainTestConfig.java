package com.example.cafeteria_infra.config;

import com.example.cafeteria.aplicacao.portas.saida.Pedidos;
import com.example.cafeteria.aplicacao.portas.saida.Pagamentos;
import com.example.cafeteria.aplicacao.textFitures.out.stub.PedidosEmMemoria;
import com.example.cafeteria.aplicacao.textFitures.out.stub.PagamentosEmMemoria;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@TestConfiguration
@Import(DomainConfig.class)
public class DomainTestConfig {

    @Bean
    public Pedidos pedidos() {
        return new PedidosEmMemoria();
    }

    @Bean
    public Pagamentos pagamentos() {
        return new PagamentosEmMemoria();
    }
}
