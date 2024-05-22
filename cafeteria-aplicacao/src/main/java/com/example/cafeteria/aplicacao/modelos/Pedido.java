package com.example.cafeteria.aplicacao.modelos;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class Pedido {

    private UUID id = UUID.randomUUID();
    private final LocalConsumoPedido localConsumoPedido;
    private final List<ItemPedido> items;
    private Status status = Status.ESPERANDO_PAGAMENTO;

    public Pedido(LocalConsumoPedido localConsumoPedido, List<ItemPedido> items) {
        this.localConsumoPedido = localConsumoPedido;
        this.items = items;
    }

    public Pedido(UUID id, LocalConsumoPedido localConsumoPedido, List<ItemPedido> items, Status status) {
        this.id = id;
        this.localConsumoPedido = localConsumoPedido;
        this.items = items;
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public LocalConsumoPedido getLocalConsumoPedido() {
        return localConsumoPedido;
    }

    public List<ItemPedido> getItems() {
        return items;
    }

    public Status getStatus() {
        return status;
    }

    public Pedido alterarStatusPedidoParaPago() {
        if (status != Status.ESPERANDO_PAGAMENTO) {
            throw new IllegalStateException("Order is already dataPagamento");
        }
        this.status = Status.PAGO;
        return this;
    }

    public Pedido alterarStatusPedidoParaEmPreparo() {
        if (status != Status.PAGO) {
            throw new IllegalStateException("Order is not dataPagamento");
        }
        status = Status.EM_PREPARO;
        return this;
    }

    public Pedido alterarStatusPedidoParaPronto() {
        if (status != Status.EM_PREPARO) {
            throw new IllegalStateException("Order is not being prepared");
        }
        status = Status.PRONTO;
        return this;
    }

    public Pedido alterarStatusPedidoParaEntregue() {
        if (status != Status.PRONTO) {
            throw new IllegalStateException("Order is not ready");
        }
        status = Status.ENTREGUE;
        return this;
    }

    public Pedido atualizarPedido(Pedido pedido) {
        if (status == Status.PAGO) {
            throw new IllegalStateException("Order is already dataPagamento");
        }
        return new Pedido(this.id, pedido.getLocalConsumoPedido(), pedido.getItems(), this.status);
    }

    public boolean pedidoPodeSerCancelado() {
        return this.status == Status.ESPERANDO_PAGAMENTO;
    }

    public BigDecimal getCusto() {
        return this.items.stream().map(ItemPedido::getCusto).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
    }
}


