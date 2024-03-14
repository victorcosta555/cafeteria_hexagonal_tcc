package com.example.coffeeshop.adapter.in.rest.resource;

import com.example.coffeeshop.application.models.Payment;

public record PaymentResponse(String cardHolderName, String cardNumber, Integer expiryMonth, Integer expiryYear) {

    public static PaymentResponse fromDomain(Payment payment) {
        var creditCard = payment.creditCard();

        return new PaymentResponse(
                creditCard.cardHolderName(),
                creditCard.cardNumber(),
                creditCard.expiryMonth().getValue(),
                creditCard.expiryYear().getValue());
    }
}
