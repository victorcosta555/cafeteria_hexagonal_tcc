package com.example.cafeteria.aplicacao.usecases;

import com.example.cafeteria.aplicacao.arquitetura.UseCase;
import com.example.cafeteria.aplicacao.portas.entrada.PreparandoCafe;
import com.example.cafeteria.aplicacao.modelos.Pedido;
import com.example.cafeteria.aplicacao.portas.saida.Pedidos;

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
