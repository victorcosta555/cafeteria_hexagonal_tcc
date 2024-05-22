package com.example.coffeeshop.application.ports.in;

import com.example.coffeeshop.application.models.CartaoDeCredito;
import com.example.coffeeshop.application.models.Pedido;
import com.example.coffeeshop.application.models.Pagamento;
import com.example.coffeeshop.application.models.Recibo;

import java.util.UUID;

public interface PedidoCafe {

    Pedido fazerPedido(Pedido pedido);
    Pedido atualizarPedido(UUID orderId, Pedido pedido);
    void cancelarPedido(UUID orderId);
    Pagamento pagarPedido(UUID orderId, CartaoDeCredito cartaoDeCredito);
    Recibo lerRecibo(UUID orderId);
    Pedido entregarPedido(UUID orderId);
}
