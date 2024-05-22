package com.example.cafeteria_infra.adapter.saida.persistencia.config;

import jakarta.transaction.Transactional;

import java.util.function.Supplier;

public class TransactionalUseCaseExecutor {

    @Transactional
    <T> T executeInTransaction(Supplier<T> execution) {
        return execution.get();
    }
}
