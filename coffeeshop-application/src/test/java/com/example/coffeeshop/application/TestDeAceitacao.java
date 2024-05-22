package com.example.coffeeshop.application;

import com.example.coffeeshop.application.models.*;
import com.example.coffeeshop.application.ports.in.PedidoCafe;
import com.example.coffeeshop.application.ports.in.PreparandoCafe;
import com.example.coffeeshop.application.ports.out.PedidoNaoEncontrado;
import com.example.coffeeshop.application.textFitures.out.stub.PedidosEmMemoria;
import com.example.coffeeshop.application.textFitures.out.stub.PagamentosEmMemoria;
import com.example.coffeeshop.application.textFitures.payment.PagamentosTestFactory;
import com.example.coffeeshop.application.usecases.MaquinaCafe;
import com.example.coffeeshop.application.usecases.Cafeteria;
import com.example.coffeeshop.application.ports.out.Pedidos;
import com.example.coffeeshop.application.ports.out.Pagamentos;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.example.coffeeshop.application.textFitures.order.PedidoTestFactory.*;
import static com.example.coffeeshop.application.textFitures.payment.CartaoDeCreditoTestFactory.aCreditCard;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TestDeAceitacao {

    private Pedidos pedidos;
    private Pagamentos pagamentos;
    private PedidoCafe customer;
    private PreparandoCafe barista;

    @BeforeEach
    public void setup() {
        pedidos = new PedidosEmMemoria();
        pagamentos = new PagamentosEmMemoria();
        customer = new Cafeteria(pedidos, pagamentos);
        barista = new MaquinaCafe(pedidos);
    }

    @Test
    void customerCanOrderCoffee() {
        var orderToMake = new Pedido(LocalConsumoPedido.LOJA, List.of(new ItemPedido(Bebida.CAPPUCCINO, TipoLeite.DESNATADO, TamanhoBebida.PEQUENO, 1)));

        var order = customer.fazerPedido(orderToMake);

        assertThat(order.getLocalConsumoPedido()).isEqualTo(LocalConsumoPedido.LOJA);
        assertThat(order.getItems()).containsExactly(new ItemPedido(Bebida.CAPPUCCINO, TipoLeite.DESNATADO, TamanhoBebida.PEQUENO, 1));
        assertThat(order.getStatus()).isEqualTo(Status.ESPERANDO_PAGAMENTO);
    }


    @Test
    void customerCanUpdateTheOrderBeforePaying() {
        var orderWithOneItem = new Pedido(LocalConsumoPedido.VIAGEM, List.of(new ItemPedido(Bebida.LATTE, TipoLeite.INTEGRAL, TamanhoBebida.GRANDE, 1)));
        var orderWithTwoItems = new Pedido(LocalConsumoPedido.VIAGEM, List.of(new ItemPedido(Bebida.LATTE, TipoLeite.INTEGRAL, TamanhoBebida.GRANDE, 2)));

        var order = customer.fazerPedido(orderWithOneItem);
        var updatedOrder = customer.atualizarPedido(order.getId(), orderWithTwoItems);

        assertThat(updatedOrder.getItems()).containsExactly(new ItemPedido(Bebida.LATTE, TipoLeite.INTEGRAL, TamanhoBebida.GRANDE, 2));
    }

    @Test
    void customerCanCancelTheOrderBeforePaying() {
        var existingOrder = pedidos.savePedido(anOrder());

        customer.cancelarPedido(existingOrder.getId());

        assertThatThrownBy(() -> pedidos.findPedidoById(existingOrder.getId())).isInstanceOf(PedidoNaoEncontrado.class);
    }

    @Test
    void customerCanPayTheOrder() {
        var existingOrder = pedidos.savePedido(anOrder());
        var creditCard = aCreditCard();

        var payment = customer.pagarPedido(existingOrder.getId(), creditCard);

        assertThat(payment.pedidoId()).isEqualTo(existingOrder.getId());
        assertThat(payment.cartaoDeCredito()).isEqualTo(creditCard);
        assertThat(pedidos.findPedidoById(existingOrder.getId()).getStatus()).isEqualTo(Status.PAGO);
    }

    @Test
    void noChangesAllowedWhenOrderIsPaid() {
        var existingOrder = pedidos.savePedido(aPaidOrder());

        assertThatThrownBy(() -> customer.atualizarPedido(existingOrder.getId(), anOrder())).isInstanceOf(IllegalStateException.class);
    }

    @Test
    void customerCanGetReceiptWhenOrderIsPaid() {
        var existingOrder = pedidos.savePedido(aPaidOrder());
        var existingPayment = pagamentos.save(PagamentosTestFactory.aPaymentForOrder(existingOrder));

        var receipt = customer.lerRecibo(existingOrder.getId());

        Assertions.assertThat(receipt.total()).isEqualTo(existingOrder.getCusto());
        Assertions.assertThat(receipt.dataPagamento()).isEqualTo(existingPayment.dataPagamento());
    }

    @Test
    void baristaCanStartPreparingTheOrderWhenItIsPaid() {
        var existingOrder = pedidos.savePedido(aPaidOrder());

        var orderInPreparation = barista.iniciarPreparacaoBebida(existingOrder.getId());

        assertThat(orderInPreparation.getStatus()).isEqualTo(Status.EM_PREPARO);
    }

    @Test
    void baristaCanMarkTheOrderReadyWhenSheIsFinishedPreparing() {
        var existingOrder = pedidos.savePedido(anOrderInPreparation());

        var preparedOrder = barista.finalizarPreparacaoBebida(existingOrder.getId());

        assertThat(preparedOrder.getStatus()).isEqualTo(Status.PRONTO);
    }

    @Test
    void customerCanTakeTheOrderWhenItIsReady() {
        var existingOrder = pedidos.savePedido(aReadyOrder());

        var takenOrder = customer.entregarPedido(existingOrder.getId());

        assertThat(takenOrder.getStatus()).isEqualTo(Status.ENTREGUE);
    }
}
