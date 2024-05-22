package com.example.coffeeshop.application.models;

import java.math.BigDecimal;
import java.time.LocalDate;

public record Recibo(BigDecimal total, LocalDate dataPagamento) {
}
