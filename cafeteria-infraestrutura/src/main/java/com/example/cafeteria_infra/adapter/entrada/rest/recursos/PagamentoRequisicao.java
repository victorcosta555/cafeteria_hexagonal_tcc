package com.example.cafeteria_infra.adapter.entrada.rest.recursos;

import javax.validation.constraints.NotNull;

public record PagamentoRequisicao(
        @NotNull String nomeUsuarioCartao,
        @NotNull String numeroCartao,
        @NotNull Integer mesValidade,
        @NotNull Integer anoValidade) {}
