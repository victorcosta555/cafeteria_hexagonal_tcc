package com.example.coffeeshop.adapter.in.rest.resource;

import com.example.coffeeshop.application.models.Drink;
import com.example.coffeeshop.application.models.LineItem;
import com.example.coffeeshop.application.models.Milk;
import com.example.coffeeshop.application.models.Size;

public record LineItemRequest(Drink drink, Milk milk, Size size, Integer quantity) {

    public LineItem toDomain() {
        return new LineItem(drink, milk, size, quantity);
    }
}
