package com.example.cafeteria.aplicacao.textFitures.payment;

import com.example.cafeteria.aplicacao.modelos.CartaoDeCredito;

import java.time.Month;
import java.time.Year;

public class CartaoDeCreditoTestFactory {

    public static CartaoDeCredito umCartaoDeCredito() {
        return new CartaoDeCredito("Michael Faraday", "11223344", Month.JANUARY, Year.of(2023));
    }
}
