package com.example.coffeeshop.application.models;

import java.math.BigDecimal;

public record LineItem(Drink drink, Milk milk, Size size, Integer quantity){

    BigDecimal getCost() {
        var price = BigDecimal.valueOf(4.0);
        if (this.size == Size.LARGE) {
            price = price.add(BigDecimal.ONE);
        }
        return price.multiply(BigDecimal.valueOf(quantity));
    }
};