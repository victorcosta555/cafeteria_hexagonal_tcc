package com.example.cafeteria.aplicacao.modelos;

import java.time.LocalDate;
import java.util.UUID;

public record Pagamento( UUID pedidoId, CartaoDeCredito cartaoDeCredito, LocalDate dataPagamento) {
}
