package com.example.coffeeshop.application.models;

import java.time.LocalDate;
import java.util.UUID;

public record Pagamento(UUID pedidoId, CartaoDeCredito cartaoDeCredito, LocalDate dataPagamento) {
}
