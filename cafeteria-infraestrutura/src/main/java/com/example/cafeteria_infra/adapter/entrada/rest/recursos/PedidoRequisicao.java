package com.example.cafeteria_infra.adapter.entrada.rest.recursos;

import com.example.cafeteria.aplicacao.modelos.LocalConsumoPedido;
import com.example.cafeteria.aplicacao.modelos.Pedido;

import java.util.List;

public record PedidoRequisicao(LocalConsumoPedido localConsumoPedido, List<ItemPedidoRequisicao> items) {

    public Pedido toDomain() {
        return new Pedido(localConsumoPedido, items.stream().map(ItemPedidoRequisicao::toDomain).toList());
    }
}
