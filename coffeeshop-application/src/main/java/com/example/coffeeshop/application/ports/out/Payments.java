package com.example.coffeeshop.application.ports.out;

import com.example.coffeeshop.application.models.Payment;

import java.util.UUID;

public interface Payments {

    Payment findPaymentByOrderId(UUID orderId);
    Payment save(Payment payment);
}
