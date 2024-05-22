package com.example.coffeeshop.adapter.in.rest.resource;

import com.example.coffeeshop.application.models.Pagamento;

public record PagamentoResposta(String nomeUsuarioCartao, String numeroCartao, Integer mesValidade, Integer anoValidade) {

    public static PagamentoResposta fromDomain(Pagamento pagamento) {
        var creditCard = pagamento.cartaoDeCredito();

        return new PagamentoResposta(
                creditCard.nomeUsuarioCartao(),
                creditCard.numeroCartao(),
                creditCard.mesValidade().getValue(),
                creditCard.anoValidade().getValue());
    }
}
