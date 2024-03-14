package com.example.coffeeshop.adapter.in.rest.resource;

import com.example.coffeeshop.application.models.Location;
import com.example.coffeeshop.application.models.Order;

import java.util.List;

public record OrderRequest(Location location, List<LineItemRequest> items) {

    public Order toDomain() {
        return new Order(location, items.stream().map(LineItemRequest::toDomain).toList());
    }
}
