package com.example.coffeeshop.application.usecases;

import com.example.coffeeshop.application.architecture.UseCase;
import com.example.coffeeshop.application.ports.in.PreparingCoffee;
import com.example.coffeeshop.application.models.Order;
import com.example.coffeeshop.application.ports.out.Orders;

import java.util.UUID;

@UseCase
public class CoffeMachine implements PreparingCoffee {

    private final Orders orders;

    public CoffeMachine(Orders orders) {
        this.orders = orders;
    }

    @Override
    public Order startPreparingOrder(UUID orderId) {
        var order = orders.findOrderById(orderId);
        return orders.saveOrder(order.markBeingPrepared());
    }

    @Override
    public Order finishPreparingOrder(UUID orderId) {
        var order = orders.findOrderById(orderId);
        return orders.saveOrder(order.markPrepared());
    }
}
