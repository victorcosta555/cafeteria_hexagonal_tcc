package com.example.coffeeshop.adapter.in.rest.resource;

import javax.validation.constraints.NotNull;

public record PagamentoRequisicao(
        @NotNull String nomeUsuarioCartao,
        @NotNull String numeroCartao,
        @NotNull Integer mesValidade,
        @NotNull Integer anoValidade) {}
