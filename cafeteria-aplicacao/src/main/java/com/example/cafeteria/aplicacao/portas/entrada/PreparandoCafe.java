package com.example.cafeteria.aplicacao.portas.entrada;

import com.example.cafeteria.aplicacao.modelos.Pedido;

import java.util.UUID;

public interface PreparandoCafe {

    Pedido iniciarPreparacaoBebida(UUID pedidoId);
    Pedido finalizarPreparacaoBebida(UUID pedidoId);
}
