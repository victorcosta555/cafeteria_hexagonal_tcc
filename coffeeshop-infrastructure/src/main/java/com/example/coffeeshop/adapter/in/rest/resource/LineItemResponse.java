package com.example.coffeeshop.adapter.in.rest.resource;

import com.example.coffeeshop.application.models.*;

public record LineItemResponse(Drink drink, Milk milk, Size size, Integer quantity) {

    public static LineItemResponse fromDomain(LineItem lineItem) {
        return new LineItemResponse(
                lineItem.drink(),
                lineItem.milk(),
                lineItem.size(),
                lineItem.quantity()
        );
    }
}
