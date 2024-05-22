package com.example.coffeeshop.application.ports.out;

import com.example.coffeeshop.application.models.Pedido;

import java.util.UUID;

public interface Pedidos {

    Pedido findPedidoById(UUID pedidoId) throws PedidoNaoEncontrado;
    Pedido savePedido(Pedido pedido);
    void deleteById(UUID pedidoId);
}
