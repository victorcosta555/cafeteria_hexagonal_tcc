package com.example.cafeteria_infra.config;

import com.example.cafeteria.aplicacao.arquitetura.UseCase;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = "com.example.cafeteria.aplicacao",
        includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, value = UseCase.class)
)
public class DomainConfig {
}
