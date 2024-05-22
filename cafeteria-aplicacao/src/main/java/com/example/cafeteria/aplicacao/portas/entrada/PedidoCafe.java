package com.example.cafeteria.aplicacao.portas.entrada;

import com.example.cafeteria.aplicacao.modelos.CartaoDeCredito;
import com.example.cafeteria.aplicacao.modelos.Recibo;
import com.example.cafeteria.aplicacao.modelos.Pedido;
import com.example.cafeteria.aplicacao.modelos.Pagamento;

import java.util.UUID;

public interface PedidoCafe {

    Pedido fazerPedido(Pedido pedido);
    Pedido atualizarPedido(UUID orderId, Pedido pedido);
    void cancelarPedido(UUID orderId);
    Pagamento pagarPedido(UUID orderId, CartaoDeCredito cartaoDeCredito);
    Recibo lerRecibo(UUID orderId);
    Pedido entregarPedido(UUID orderId);
}
