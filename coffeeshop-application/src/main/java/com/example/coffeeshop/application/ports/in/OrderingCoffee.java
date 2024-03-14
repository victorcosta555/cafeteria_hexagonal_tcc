package com.example.coffeeshop.application.ports.in;

import com.example.coffeeshop.application.models.CreditCard;
import com.example.coffeeshop.application.models.Order;
import com.example.coffeeshop.application.models.Payment;
import com.example.coffeeshop.application.models.Receipt;

import java.util.UUID;

public interface OrderingCoffee {

    Order placeOrder(Order order);
    Order updateOrder(UUID orderId, Order order);
    void cancelOrder(UUID orderId);
    Payment payOrder(UUID orderId, CreditCard creditCard);
    Receipt readReceipt(UUID orderId);
    Order takeOrder(UUID orderId);
}
