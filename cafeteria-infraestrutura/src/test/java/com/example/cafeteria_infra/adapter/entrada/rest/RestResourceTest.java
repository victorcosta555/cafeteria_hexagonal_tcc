package com.example.cafeteria_infra.adapter.entrada.rest;

import com.example.cafeteria_infra.config.DomainTestConfig;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@WebMvcTest
@Import(DomainTestConfig.class)
public @interface RestResourceTest {
}
