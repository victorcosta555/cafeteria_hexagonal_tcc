package com.example.coffeeshop.adapter.in.rest.resource;

import com.example.coffeeshop.application.models.LocalConsumoPedido;
import com.example.coffeeshop.application.models.Pedido;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record OrderResponse(UUID id, LocalConsumoPedido localConsumoPedido, List<ItemPedidoResposta> items, BigDecimal cost) {

    public static OrderResponse fromDomain(Pedido pedido) {
        return new OrderResponse(
                pedido.getId(),
                pedido.getLocalConsumoPedido(),
                pedido.getItems().stream().map(ItemPedidoResposta::fromDomain).toList(),
                pedido.getCusto()
        );
    }
}
