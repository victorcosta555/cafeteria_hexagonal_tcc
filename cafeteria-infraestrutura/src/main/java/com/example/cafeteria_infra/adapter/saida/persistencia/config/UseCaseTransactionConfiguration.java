package com.example.cafeteria_infra.adapter.saida.persistencia.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class UseCaseTransactionConfiguration {

    @Bean
    public TransactionalUseCaseAspect transactionalUseCaseAspect(TransactionalUseCaseExecutor transactionalUseCaseExecutor) {
        return new TransactionalUseCaseAspect(transactionalUseCaseExecutor);
    }

    @Bean
    public TransactionalUseCaseExecutor transactionalUseCaseExecutor() {
        return new TransactionalUseCaseExecutor();
    }
}
