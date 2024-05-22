package com.example.coffeeshop.application.ports.in;

import com.example.coffeeshop.application.models.Pedido;

import java.util.UUID;

public interface PreparandoCafe {

    Pedido iniciarPreparacaoBebida(UUID pedidoId);
    Pedido finalizarPreparacaoBebida(UUID pedidoId);
}
