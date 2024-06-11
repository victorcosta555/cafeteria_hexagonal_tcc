package com.example.cafeteria.aplicacao;

import com.example.cafeteria.aplicacao.modelos.*;
import com.example.cafeteria.aplicacao.portas.entrada.PedidoCafe;
import com.example.cafeteria.aplicacao.portas.entrada.PreparandoCafe;
import com.example.cafeteria.aplicacao.portas.saida.Pagamentos;
import com.example.cafeteria.aplicacao.portas.saida.PedidoNaoEncontrado;
import com.example.cafeteria.aplicacao.portas.saida.Pedidos;
import com.example.cafeteria.aplicacao.textFitures.out.stub.PagamentosEmMemoria;
import com.example.cafeteria.aplicacao.textFitures.out.stub.PedidosEmMemoria;
import com.example.cafeteria.aplicacao.textFitures.payment.CartaoDeCreditoTestFactory;
import com.example.cafeteria.aplicacao.textFitures.payment.PagamentosTestFactory;
import com.example.cafeteria.aplicacao.usecases.Cafeteria;
import com.example.cafeteria.aplicacao.usecases.MaquinaCafe;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.example.cafeteria.aplicacao.textFitures.order.PedidoTestFactory.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TestDeAceitacao {

    private Pedidos pedidos;
    private Pagamentos pagamentos;
    private PedidoCafe cafeteria;
    private PreparandoCafe barista;

    @BeforeEach
    public void setup() {
        pedidos = new PedidosEmMemoria();
        pagamentos = new PagamentosEmMemoria();
        cafeteria = new Cafeteria(pedidos, pagamentos);
        barista = new MaquinaCafe(pedidos);
    }

    @Test
    void cliente_pode_fazer_pedido() {
        var pedidoFeito = new Pedido(LocalConsumoPedido.LOJA,
                List.of(new ItemPedido(Bebida.CAPPUCCINO, TipoLeite.DESNATADO, TamanhoBebida.PEQUENO, 1)));

        var compra = cafeteria.fazerPedido(pedidoFeito);

        Assertions.assertThat(compra.getLocalConsumoPedido())
                .isEqualTo(LocalConsumoPedido.LOJA);

        assertThat(compra.getItems())
                .containsExactly(new ItemPedido(Bebida.CAPPUCCINO, TipoLeite.DESNATADO, TamanhoBebida.PEQUENO, 1));

        assertThat(compra.getStatus()).isEqualTo(Status.ESPERANDO_PAGAMENTO);
    }


    @Test
    void cliente_pode_atualizar_pedido_antes_pagamento() {
        var compraComUmItem = new Pedido(LocalConsumoPedido.VIAGEM,
                List.of(new ItemPedido(Bebida.LATTE, TipoLeite.INTEGRAL, TamanhoBebida.GRANDE, 1)));

        var compraComDoisItems = new Pedido(LocalConsumoPedido.VIAGEM,
                List.of(new ItemPedido(Bebida.LATTE, TipoLeite.INTEGRAL, TamanhoBebida.GRANDE, 2)));

        var compra = cafeteria.fazerPedido(compraComUmItem);
        var compraAtualizada = cafeteria.atualizarPedido(compra.getId(), compraComDoisItems);

        assertThat(compraAtualizada.getItems())
                .containsExactly(new ItemPedido(Bebida.LATTE, TipoLeite.INTEGRAL, TamanhoBebida.GRANDE, 2));
    }

    @Test
    void cliente_pode_cancelar_pedido_antes_do_pagamento() {
        var pedidoSalvo = pedidos.savePedido(umPedido());

        cafeteria.cancelarPedido(pedidoSalvo.getId());

        assertThatThrownBy(() -> pedidos.findPedidoById(pedidoSalvo.getId())).isInstanceOf(PedidoNaoEncontrado.class);
    }

    @Test
    void cliente_pode_pagar_pedido() {
        var pedidoSalvo = pedidos.savePedido(umPedido());
        var cartaoDeCredito = CartaoDeCreditoTestFactory.umCartaoDeCredito();

        var pagamento = cafeteria.pagarPedido(pedidoSalvo.getId(), cartaoDeCredito);

        assertThat(pagamento.pedidoId()).isEqualTo(pedidoSalvo.getId());
        assertThat(pagamento.cartaoDeCredito()).isEqualTo(cartaoDeCredito);
        assertThat(pedidos.findPedidoById(pedidoSalvo.getId()).getStatus()).isEqualTo(Status.PAGO);
    }

    @Test
    void nenhuma_mudanca_e_possivel_apos_pagamento() {
        var pedidoSalvo = pedidos.savePedido(umPedidoPago());

        assertThatThrownBy(() -> cafeteria.atualizarPedido(pedidoSalvo.getId(), umPedido())).isInstanceOf(IllegalStateException.class);
    }

    @Test
    void cliente_pode_gerar_recibo_quando_a_pedido_esta_pago() {
        var pedidoSalvo = pedidos.savePedido(umPedidoPago());
        var pagamentoSalvo = pagamentos.save(PagamentosTestFactory.umPagametoParaPedido(pedidoSalvo));

        var receipt = cafeteria.lerRecibo(pedidoSalvo.getId());

        assertThat(receipt.total()).isEqualTo(pedidoSalvo.getCusto());
        assertThat(receipt.dataPagamento()).isEqualTo(pagamentoSalvo.dataPagamento());
    }

    @Test
    void barista_pode_preparar_bebida_apos_pedido_ser_paga() {
        var pedidoSalvo = pedidos.savePedido(umPedidoPago());

        var orderInPreparation = barista.iniciarPreparacaoBebida(pedidoSalvo.getId());

        assertThat(orderInPreparation.getStatus()).isEqualTo(Status.EM_PREPARO);
    }

    @Test
    void barista_pode_alterar_status_do_pedido_para_pronto_quando_finalizar_preparo() {
        var pedidoSalvo = pedidos.savePedido(umPedidoEmPreparacao());

        var preparedOrder = barista.finalizarPreparacaoBebida(pedidoSalvo.getId());

        assertThat(preparedOrder.getStatus()).isEqualTo(Status.PRONTO);
    }

    @Test
    void cliente_pode_retirar_pedido_apos_status_pronto() {
        var pedidoSalvo = pedidos.savePedido(umPedidoPronto());

        var takenOrder = cafeteria.entregarPedido(pedidoSalvo.getId());

        assertThat(takenOrder.getStatus()).isEqualTo(Status.ENTREGUE);
    }
}
