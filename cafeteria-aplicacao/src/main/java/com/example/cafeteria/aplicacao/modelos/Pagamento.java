package com.example.cafeteria.aplicacao.modelos;

import java.time.LocalDate;
import java.util.UUID;

public class Pagamento {

    private UUID pedidoId;
    private CartaoDeCredito cartaoDeCredito;
    private LocalDate dataPagamento;

    public Pagamento() {
    }

    public Pagamento(UUID pedidoId, CartaoDeCredito cartaoDeCredito, LocalDate dataPagamento) {
        this.pedidoId = pedidoId;
        this.cartaoDeCredito = cartaoDeCredito;
        this.dataPagamento = dataPagamento;
    }

    public UUID getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(UUID pedidoId) {
        this.pedidoId = pedidoId;
    }

    public CartaoDeCredito getCartaoDeCredito() {
        return cartaoDeCredito;
    }

    public void setCartaoDeCredito(CartaoDeCredito cartaoDeCredito) {
        this.cartaoDeCredito = cartaoDeCredito;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }
}
