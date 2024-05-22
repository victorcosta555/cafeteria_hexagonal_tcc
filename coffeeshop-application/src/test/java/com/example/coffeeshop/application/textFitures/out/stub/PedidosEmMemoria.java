package com.example.coffeeshop.application.textFitures.out.stub;

import com.example.coffeeshop.application.models.Pedido;
import com.example.coffeeshop.application.ports.out.PedidoNaoEncontrado;
import com.example.coffeeshop.application.ports.out.Pedidos;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class PedidosEmMemoria implements Pedidos {

    private final Map<UUID, Pedido> entities = new HashMap<>();

    @Override
    public Pedido findPedidoById(UUID pedidoId) throws PedidoNaoEncontrado {
        var order = entities.get(pedidoId);

        if (Objects.isNull(order))
            throw new PedidoNaoEncontrado();

        return order;
    }

    @Override
    public Pedido savePedido(Pedido pedido) {
        entities.put(pedido.getId(), pedido);
        return pedido;
    }

    @Override
    public void deleteById(UUID pedidoId) {
        entities.remove(pedidoId);
    }
}
