package com.example.coffeeshop.application.ports.out;

import com.example.coffeeshop.application.models.Order;

import java.util.UUID;

public interface Orders {

    Order findOrderById(UUID orderId) throws OrderNotFound;
    Order saveOrder(Order order);
    void deleteById(UUID orderId);
}
