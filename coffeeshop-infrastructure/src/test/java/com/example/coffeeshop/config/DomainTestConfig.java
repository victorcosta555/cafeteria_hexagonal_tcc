package com.example.coffeeshop.config;

import com.example.coffeeshop.application.ports.out.Pedidos;
import com.example.coffeeshop.application.ports.out.Pagamentos;
import com.example.coffeeshop.application.textFitures.out.stub.PedidosEmMemoria;
import com.example.coffeeshop.application.textFitures.out.stub.PagamentosEmMemoria;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@TestConfiguration
@Import(DomainConfig.class)
public class DomainTestConfig {

    @Bean
    public Pedidos orders() {
        return new PedidosEmMemoria();
    }

    @Bean
    public Pagamentos payments() {
        return new PagamentosEmMemoria();
    }
}
