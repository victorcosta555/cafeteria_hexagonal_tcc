package com.example.cafeteria.aplicacao.portas.saida;

import com.example.cafeteria.aplicacao.modelos.Pedido;

import java.util.UUID;

public interface Pedidos {

    Pedido findPedidoById(UUID pedidoId) throws PedidoNaoEncontrado;
    Pedido savePedido(Pedido pedido);
    void deleteById(UUID pedidoId);
}
