package com.example.cafeteria.aplicacao.modelos;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Recibo {

    private BigDecimal total;
    private LocalDate dataPagamento;

    public Recibo() {
    }

    public Recibo(BigDecimal total, LocalDate dataPagamento) {
        this.total = total;
        this.dataPagamento = dataPagamento;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }
}
