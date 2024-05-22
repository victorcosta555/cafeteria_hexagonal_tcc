package com.example.coffeeshop.adapter.in.rest.resource;

import com.example.coffeeshop.application.models.Recibo;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ReceiptResponse(BigDecimal amount, LocalDate paid) {

    public static ReceiptResponse fromDomain(Recibo recibo) {
        return new ReceiptResponse(recibo.total(), recibo.dataPagamento());
    }
}
