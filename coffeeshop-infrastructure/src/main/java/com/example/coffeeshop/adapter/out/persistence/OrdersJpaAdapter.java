package com.example.coffeeshop.adapter.out.persistence;

import com.example.coffeeshop.adapter.out.persistence.entity.OrderEntity;
import com.example.coffeeshop.application.models.Order;
import com.example.coffeeshop.application.ports.out.OrderNotFound;
import com.example.coffeeshop.application.ports.out.Orders;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class OrdersJpaAdapter implements Orders {

    private final OrderJpaRepository orderJpaRepository;

    @Override
    public Order findOrderById(UUID orderId) throws OrderNotFound {
        return orderJpaRepository.findById(orderId)
                .map(OrderEntity::toDomain)
                .orElseThrow(OrderNotFound::new);
    }

    @Override
    public Order saveOrder(Order order) {
        return orderJpaRepository.save(OrderEntity.fromDomain(order)).toDomain();
    }

    @Override
    public void deleteById(UUID orderId) {
        orderJpaRepository.deleteById(orderId);
    }
}
