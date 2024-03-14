package com.example.coffeeshop.application.ports.in;

import com.example.coffeeshop.application.models.Order;

import java.util.UUID;

public interface PreparingCoffee {

    Order startPreparingOrder(UUID orderId);
    Order finishPreparingOrder(UUID orderId);
}
