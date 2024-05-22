package com.example.coffeeshop.application.usecases;

import com.example.coffeeshop.application.architecture.UseCase;
import com.example.coffeeshop.application.ports.in.PreparandoCafe;
import com.example.coffeeshop.application.models.Pedido;
import com.example.coffeeshop.application.ports.out.Pedidos;

import java.util.UUID;

@UseCase
public class MaquinaCafe implements PreparandoCafe {

    private final Pedidos pedidos;

    public MaquinaCafe(Pedidos pedidos) {
        this.pedidos = pedidos;
    }

    @Override
    public Pedido iniciarPreparacaoBebida(UUID pedidoId) {
        var pedido = pedidos.findPedidoById(pedidoId);
        return pedidos.savePedido(pedido.alterarStatusPedidoParaEmPreparo());
    }

    @Override
    public Pedido finalizarPreparacaoBebida(UUID pedidoId) {
        var pedido = pedidos.findPedidoById(pedidoId);
        return pedidos.savePedido(pedido.alterarStatusPedidoParaPronto());
    }
}
