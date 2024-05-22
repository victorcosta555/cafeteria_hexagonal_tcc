package com.example.coffeeshop.application.textFitures.order;

import com.example.coffeeshop.application.models.*;

import java.util.List;

public class PedidoTestFactory {

    public static Pedido anOrder() {
        return new Pedido(LocalConsumoPedido.VIAGEM, List.of(new ItemPedido(Bebida.LATTE, TipoLeite.INTEGRAL, TamanhoBebida.GRANDE, 1)));
    }

    public static Pedido aPaidOrder() {
        return anOrder()
                .alterarStatusPedidoParaPago();
    }

    public static Pedido anOrderInPreparation() {
        return aPaidOrder()
                .alterarStatusPedidoParaEmPreparo();
    }

    public static Pedido aReadyOrder() {
        return anOrderInPreparation()
                .alterarStatusPedidoParaPronto();
    }
}
