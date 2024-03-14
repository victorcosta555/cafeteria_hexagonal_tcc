package com.example.coffeeshop.application.textFitures.payment;

import com.example.coffeeshop.application.models.Order;
import com.example.coffeeshop.application.models.Payment;

import java.time.LocalDate;

import static com.example.coffeeshop.application.textFitures.payment.CreditCardTestFactory.aCreditCard;

public class PaymentTestFactory {

    public static Payment aPaymentForOrder(Order order) {
        return new Payment(order.getId(), aCreditCard(), LocalDate.now());
    }
}
