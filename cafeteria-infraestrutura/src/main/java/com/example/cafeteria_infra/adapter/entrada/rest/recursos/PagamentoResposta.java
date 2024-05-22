package com.example.cafeteria_infra.adapter.entrada.rest.recursos;

import com.example.cafeteria.aplicacao.modelos.Pagamento;

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
