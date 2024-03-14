package com.example.coffeeshop.application;

import com.example.coffeeshop.application.models.*;
import com.example.coffeeshop.application.ports.in.OrderingCoffee;
import com.example.coffeeshop.application.ports.in.PreparingCoffee;
import com.example.coffeeshop.application.ports.out.OrderNotFound;
import com.example.coffeeshop.application.textFitures.out.stub.InMemoryOrders;
import com.example.coffeeshop.application.textFitures.out.stub.InMemoryPayments;
import com.example.coffeeshop.application.textFitures.payment.PaymentTestFactory;
import com.example.coffeeshop.application.usecases.CoffeMachine;
import com.example.coffeeshop.application.usecases.CoffeeShop;
import com.example.coffeeshop.application.ports.out.Orders;
import com.example.coffeeshop.application.ports.out.Payments;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.example.coffeeshop.application.textFitures.order.OrderTestFactory.*;
import static com.example.coffeeshop.application.textFitures.payment.CreditCardTestFactory.aCreditCard;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AcceptanceTests {

    private Orders orders;
    private Payments payments;
    private OrderingCoffee customer;
    private PreparingCoffee barista;

    @BeforeEach
    public void setup() {
        orders = new InMemoryOrders();
        payments = new InMemoryPayments();
        customer = new CoffeeShop(orders, payments);
        barista = new CoffeMachine(orders);
    }

    @Test
    void customerCanOrderCoffee() {
        var orderToMake = new Order(Location.IN_STORE, List.of(new LineItem(Drink.CAPPUCCINO, Milk.SKIMMED, Size.SMALL, 1)));

        var order = customer.placeOrder(orderToMake);

        assertThat(order.getLocation()).isEqualTo(Location.IN_STORE);
        assertThat(order.getItems()).containsExactly(new LineItem(Drink.CAPPUCCINO, Milk.SKIMMED, Size.SMALL, 1));
        assertThat(order.getStatus()).isEqualTo(Status.PAYMENT_EXPECTED);
    }


    @Test
    void customerCanUpdateTheOrderBeforePaying() {
        var orderWithOneItem = new Order(Location.TAKE_AWAY, List.of(new LineItem(Drink.LATTE, Milk.WHOLE, Size.LARGE, 1)));
        var orderWithTwoItems = new Order(Location.TAKE_AWAY, List.of(new LineItem(Drink.LATTE, Milk.WHOLE, Size.LARGE, 2)));

        var order = customer.placeOrder(orderWithOneItem);
        var updatedOrder = customer.updateOrder(order.getId(), orderWithTwoItems);

        assertThat(updatedOrder.getItems()).containsExactly(new LineItem(Drink.LATTE, Milk.WHOLE, Size.LARGE, 2));
    }

    @Test
    void customerCanCancelTheOrderBeforePaying() {
        var existingOrder = orders.saveOrder(anOrder());

        customer.cancelOrder(existingOrder.getId());

        assertThatThrownBy(() -> orders.findOrderById(existingOrder.getId())).isInstanceOf(OrderNotFound.class);
    }

    @Test
    void customerCanPayTheOrder() {
        var existingOrder = orders.saveOrder(anOrder());
        var creditCard = aCreditCard();

        var payment = customer.payOrder(existingOrder.getId(), creditCard);

        assertThat(payment.orderId()).isEqualTo(existingOrder.getId());
        assertThat(payment.creditCard()).isEqualTo(creditCard);
        assertThat(orders.findOrderById(existingOrder.getId()).getStatus()).isEqualTo(Status.PAID);
    }

    @Test
    void noChangesAllowedWhenOrderIsPaid() {
        var existingOrder = orders.saveOrder(aPaidOrder());

        assertThatThrownBy(() -> customer.updateOrder(existingOrder.getId(), anOrder())).isInstanceOf(IllegalStateException.class);
    }

    @Test
    void customerCanGetReceiptWhenOrderIsPaid() {
        var existingOrder = orders.saveOrder(aPaidOrder());
        var existingPayment = payments.save(PaymentTestFactory.aPaymentForOrder(existingOrder));

        var receipt = customer.readReceipt(existingOrder.getId());

        Assertions.assertThat(receipt.amount()).isEqualTo(existingOrder.getCost());
        Assertions.assertThat(receipt.paid()).isEqualTo(existingPayment.paid());
    }

    @Test
    void baristaCanStartPreparingTheOrderWhenItIsPaid() {
        var existingOrder = orders.saveOrder(aPaidOrder());

        var orderInPreparation = barista.startPreparingOrder(existingOrder.getId());

        assertThat(orderInPreparation.getStatus()).isEqualTo(Status.PREPARING);
    }

    @Test
    void baristaCanMarkTheOrderReadyWhenSheIsFinishedPreparing() {
        var existingOrder = orders.saveOrder(anOrderInPreparation());

        var preparedOrder = barista.finishPreparingOrder(existingOrder.getId());

        assertThat(preparedOrder.getStatus()).isEqualTo(Status.READY);
    }

    @Test
    void customerCanTakeTheOrderWhenItIsReady() {
        var existingOrder = orders.saveOrder(aReadyOrder());

        var takenOrder = customer.takeOrder(existingOrder.getId());

        assertThat(takenOrder.getStatus()).isEqualTo(Status.TAKEN);
    }
}
