package com.example.coffeeshop.application.textFitures.payment;

import com.example.coffeeshop.application.models.CartaoDeCredito;

import java.time.Month;
import java.time.Year;

public class CartaoDeCreditoTestFactory {

    public static CartaoDeCredito aCreditCard() {
        return new CartaoDeCredito("Michael Faraday", "11223344", Month.JANUARY, Year.of(2023));
    }
}
