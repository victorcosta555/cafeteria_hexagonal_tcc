package com.example.coffeeshop.application.textFitures.payment;

import com.example.coffeeshop.application.models.Pedido;
import com.example.coffeeshop.application.models.Pagamento;

import java.time.LocalDate;

import static com.example.coffeeshop.application.textFitures.payment.CartaoDeCreditoTestFactory.aCreditCard;

public class PagamentosTestFactory {

    public static Pagamento aPaymentForOrder(Pedido pedido) {
        return new Pagamento(pedido.getId(), aCreditCard(), LocalDate.now());
    }
}
