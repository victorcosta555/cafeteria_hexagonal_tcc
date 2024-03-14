package com.example.coffeeshop.application.textFitures.out.stub;

import com.example.coffeeshop.application.models.Order;
import com.example.coffeeshop.application.ports.out.OrderNotFound;
import com.example.coffeeshop.application.ports.out.Orders;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class InMemoryOrders implements Orders {

    private final Map<UUID, Order> entities = new HashMap<>();

    @Override
    public Order findOrderById(UUID orderId) throws OrderNotFound {
        var order = entities.get(orderId);

        if (Objects.isNull(order))
            throw new OrderNotFound();

        return order;
    }

    @Override
    public Order saveOrder(Order order) {
        entities.put(order.getId(), order);
        return order;
    }

    @Override
    public void deleteById(UUID orderId) {
        entities.remove(orderId);
    }
}
