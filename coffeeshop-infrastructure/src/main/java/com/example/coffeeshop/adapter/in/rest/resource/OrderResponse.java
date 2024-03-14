package com.example.coffeeshop.adapter.in.rest.resource;

import com.example.coffeeshop.application.models.Location;
import com.example.coffeeshop.application.models.Order;

import java.math.BigDecimal;
import java.util.List;

public record OrderResponse(Location location, List<LineItemResponse> items, BigDecimal cost) {

    public static OrderResponse fromDomain(Order order) {
        return new OrderResponse(
                order.getLocation(),
                order.getItems().stream().map(LineItemResponse::fromDomain).toList(),
                order.getCost()
        );
    }
}
