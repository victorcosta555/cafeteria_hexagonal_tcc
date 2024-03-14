package com.example.coffeeshop.adapter.out.persistence;

import com.example.coffeeshop.application.models.*;
import com.example.coffeeshop.application.ports.out.OrderNotFound;
import com.example.coffeeshop.application.ports.out.Orders;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@PersistenceTest
public class OrdersJpaAdapterTest {

    @Autowired
    private Orders orders;

    @Autowired
    private OrderJpaRepository orderJpaRepository;


    @Test
    void creatingOrderReturnsPersistedOrder() {
        var item = new LineItem(Drink.LATTE, Milk.WHOLE, Size.SMALL, 1);
        var order = new Order(Location.TAKE_AWAY, List.of(
                item
        ));

        var persistetdOrder = orders.saveOrder(order);

        assertThat(persistetdOrder.getLocation()).isEqualTo(Location.TAKE_AWAY);
        assertThat(persistetdOrder.getItems()).containsExactly(item);
    }

    @Test
    @Sql("classpath:data/order.sql")
    void findingPreviouslyPersistedOrderReturnsDetails() {
        var order = orders.findOrderById(UUID.fromString("757d9c0f-400f-4137-9aea-83e64ba3efb2"));

        assertThat(order.getLocation()).isEqualTo(Location.IN_STORE);
        assertThat(order.getItems()).containsExactly(new LineItem(Drink.ESPRESSO, Milk.SKIMMED, Size.LARGE, 1));
    }

    @Test
    void findingNonExistingOrderThrowsException() {
        assertThatThrownBy(() -> orders.findOrderById(UUID.randomUUID())).isInstanceOf(OrderNotFound.class);
    }

    @Test
    @Sql("classpath:data/order.sql")
    void deletesPreviouslyPersistedOrder() {
        orders.deleteById(UUID.fromString("757d9c0f-400f-4137-9aea-83e64ba3efb2"));

        assertThat(orderJpaRepository.findById(UUID.fromString("757d9c0f-400f-4137-9aea-83e64ba3efb2"))).isEmpty();
    }

}
