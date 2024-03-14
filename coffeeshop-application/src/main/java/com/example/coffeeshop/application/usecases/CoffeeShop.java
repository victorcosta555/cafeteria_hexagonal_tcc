package com.example.coffeeshop.application.usecases;

import com.example.coffeeshop.application.architecture.UseCase;
import com.example.coffeeshop.application.models.CreditCard;
import com.example.coffeeshop.application.models.Receipt;
import com.example.coffeeshop.application.ports.in.OrderingCoffee;
import com.example.coffeeshop.application.models.Order;
import com.example.coffeeshop.application.models.Payment;
import com.example.coffeeshop.application.ports.out.Orders;
import com.example.coffeeshop.application.ports.out.Payments;

import java.time.LocalDate;
import java.util.UUID;

@UseCase
public class CoffeeShop implements OrderingCoffee {

    private final Orders orders;
    private final Payments payments;

    public CoffeeShop(Orders orders, Payments payments) {
        this.orders = orders;
        this.payments = payments;
    }

    @Override
    public Order placeOrder(Order order) {
        return orders.saveOrder(order);
    }

    @Override
    public Order updateOrder(UUID orderId, Order order) {
        var existingOrder = orders.findOrderById(orderId);

        return orders.saveOrder(existingOrder.update(order));
    }

    @Override
    public void cancelOrder(UUID orderId) {
        var order = orders.findOrderById(orderId);

        if (!order.canBeCancelled()) {
            throw new IllegalStateException("Order is already paid");
        }

        orders.deleteById(orderId);
    }

    @Override
    public Payment payOrder(UUID orderId, CreditCard creditCard) {
        var order = orders.findOrderById(orderId);
        orders.saveOrder(order.markPaid());
        return payments.save(new Payment(orderId, creditCard, LocalDate.now()));
    }

    @Override
    public Receipt readReceipt(UUID orderId) {
        var order = orders.findOrderById(orderId);
        var payment = payments.findPaymentByOrderId(orderId);

        return new Receipt(order.getCost(), payment.paid());
    }

    @Override
    public Order takeOrder(UUID orderId) {
        var order = orders.findOrderById(orderId);

        return orders.saveOrder(order.markTaken());
    }
}
