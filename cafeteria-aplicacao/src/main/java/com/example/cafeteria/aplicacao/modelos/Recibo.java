package com.example.cafeteria.aplicacao.modelos;

import java.math.BigDecimal;
import java.time.LocalDate;

public record Recibo(BigDecimal total, LocalDate dataPagamento) {
}
