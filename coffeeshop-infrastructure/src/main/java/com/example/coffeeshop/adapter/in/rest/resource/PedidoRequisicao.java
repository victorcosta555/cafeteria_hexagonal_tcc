package com.example.coffeeshop.adapter.in.rest.resource;

import com.example.coffeeshop.application.models.LocalConsumoPedido;
import com.example.coffeeshop.application.models.Pedido;

import java.util.List;

public record PedidoRequisicao(LocalConsumoPedido localConsumoPedido, List<ItemPedidoRequisicao> items) {

    public Pedido toDomain() {
        return new Pedido(localConsumoPedido, items.stream().map(ItemPedidoRequisicao::toDomain).toList());
    }
}
