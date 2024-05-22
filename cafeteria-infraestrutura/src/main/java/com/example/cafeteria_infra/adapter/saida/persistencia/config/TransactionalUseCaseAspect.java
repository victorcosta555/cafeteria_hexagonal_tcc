package com.example.cafeteria_infra.adapter.saida.persistencia.config;

import com.example.cafeteria.aplicacao.arquitetura.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
@RequiredArgsConstructor
public class TransactionalUseCaseAspect {

    private final TransactionalUseCaseExecutor transactionalUseCaseExecutor;

    @Pointcut("@within(useCase)")
    public void inUseCase(UseCase useCase) {
    }

    @Around("inUseCase(useCase)")
    public Object useCase(ProceedingJoinPoint proceedingJoinPoint, UseCase useCase) {
        return transactionalUseCaseExecutor.executeInTransaction(() -> proceed(proceedingJoinPoint));
    }

    @SneakyThrows
    private static Object proceed(ProceedingJoinPoint joinPoint) {
        return joinPoint.proceed();
    }
}
