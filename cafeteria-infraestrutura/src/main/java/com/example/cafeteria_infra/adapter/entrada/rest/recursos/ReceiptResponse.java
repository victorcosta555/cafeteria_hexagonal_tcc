package com.example.cafeteria_infra.adapter.entrada.rest.recursos;

import com.example.cafeteria.aplicacao.modelos.Recibo;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ReceiptResponse(BigDecimal amount, LocalDate paid) {

    public static ReceiptResponse fromDomain(Recibo recibo) {
        return new ReceiptResponse(recibo.total(), recibo.dataPagamento());
    }
}
