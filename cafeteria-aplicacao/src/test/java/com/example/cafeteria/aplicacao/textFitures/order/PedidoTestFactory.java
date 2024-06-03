package com.example.cafeteria.aplicacao.textFitures.order;

import com.example.cafeteria.aplicacao.modelos.*;

import java.util.List;

public class PedidoTestFactory {

    public static Pedido umPedido() {
        return new Pedido(LocalConsumoPedido.VIAGEM, List.of(new ItemPedido(Bebida.LATTE, TipoLeite.INTEGRAL, TamanhoBebida.GRANDE, 1)));
    }

    public static Pedido umPedidoPago() {
        return umPedido()
                .alterarStatusPedidoParaPago();
    }

    public static Pedido umPedidoEmPreparacao() {
        return umPedidoPago()
                .alterarStatusPedidoParaEmPreparo();
    }

    public static Pedido umPedidoPronto() {
        return umPedidoEmPreparacao()
                .alterarStatusPedidoParaPronto();
    }
}
