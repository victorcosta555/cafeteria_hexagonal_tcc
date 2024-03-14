package com.example.coffeeshop.config;

import com.example.coffeeshop.application.ports.out.Orders;
import com.example.coffeeshop.application.ports.out.Payments;
import com.example.coffeeshop.application.textFitures.out.stub.InMemoryOrders;
import com.example.coffeeshop.application.textFitures.out.stub.InMemoryPayments;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@TestConfiguration
@Import(DomainConfig.class)
public class DomainTestConfig {

    @Bean
    public Orders orders() {
        return new InMemoryOrders();
    }

    @Bean
    public Payments payments() {
        return new InMemoryPayments();
    }
}
