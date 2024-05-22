package com.example.coffeeshop.application.usecases;

import com.example.coffeeshop.application.architecture.UseCase;
import com.example.coffeeshop.application.models.CartaoDeCredito;
import com.example.coffeeshop.application.models.Pagamento;
import com.example.coffeeshop.application.models.Pedido;
import com.example.coffeeshop.application.models.Recibo;
import com.example.coffeeshop.application.ports.in.PedidoCafe;
import com.example.coffeeshop.application.ports.out.Pedidos;
import com.example.coffeeshop.application.ports.out.Pagamentos;

import java.time.LocalDate;
import java.util.UUID;

@UseCase
public class Cafeteria implements PedidoCafe {

    private final Pedidos pedidos;
    private final Pagamentos pagamentos;

    public Cafeteria(Pedidos pedidos, Pagamentos pagamentos) {
        this.pedidos = pedidos;
        this.pagamentos = pagamentos;
    }

    @Override
    public Pedido fazerPedido(Pedido pedido) {
        return pedidos.savePedido(pedido);
    }

    @Override
    public Pedido atualizarPedido(UUID orderId, Pedido pedido) {
        var pedidoExistente = pedidos.findPedidoById(orderId);

        return pedidos.savePedido(pedidoExistente.atualizarPedido(pedido));
    }

    @Override
    public void cancelarPedido(UUID pedidoId) {
        var pedido = pedidos.findPedidoById(pedidoId);

        if (!pedido.pedidoPodeSerCancelado()) {
            throw new IllegalStateException("Pedido já esta pago");
        }

        pedidos.deleteById(pedidoId);
    }

    @Override
    public Pagamento pagarPedido(UUID pedidoId, CartaoDeCredito cartaoDeCredito) {
        var pedido = pedidos.findPedidoById(pedidoId);
        pedidos.savePedido(pedido.alterarStatusPedidoParaPago());
        return pagamentos.save(new Pagamento(pedidoId, cartaoDeCredito, LocalDate.now()));
    }

    @Override
    public Recibo lerRecibo(UUID pedidoId) {
        var pedido = pedidos.findPedidoById(pedidoId);
        var pagamento = pagamentos.findPagamentoByPedidoId(pedidoId);

        return new Recibo(pedido.getCusto(), pagamento.dataPagamento());
    }

    @Override
    public Pedido entregarPedido(UUID pedidoId) {
        var pedido = pedidos.findPedidoById(pedidoId);

        return pedidos.savePedido(pedido.alterarStatusPedidoParaEntregue());
    }
}
