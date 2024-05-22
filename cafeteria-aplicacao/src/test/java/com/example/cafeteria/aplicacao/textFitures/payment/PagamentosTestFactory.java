package com.example.cafeteria.aplicacao.textFitures.payment;

import com.example.cafeteria.aplicacao.modelos.Pedido;
import com.example.cafeteria.aplicacao.modelos.Pagamento;

import java.time.LocalDate;

import static com.example.cafeteria.aplicacao.textFitures.payment.CartaoDeCreditoTestFactory.aCreditCard;

public class PagamentosTestFactory {

    public static Pagamento aPaymentForOrder(Pedido pedido) {
        return new Pagamento(pedido.getId(), aCreditCard(), LocalDate.now());
    }
}
