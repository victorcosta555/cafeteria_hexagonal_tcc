package com.example.coffeeshop.adapter.in.rest;

import com.example.coffeeshop.adapter.in.rest.resource.PaymentRequest;
import com.example.coffeeshop.adapter.in.rest.resource.PaymentResponse;
import com.example.coffeeshop.application.models.CreditCard;
import com.example.coffeeshop.application.ports.in.OrderingCoffee;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.Month;
import java.time.Year;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final OrderingCoffee orderingCoffee;

    @PutMapping("/{id}")
    public ResponseEntity<PaymentResponse> payOrder(@PathVariable UUID id, @Valid @RequestBody PaymentRequest request) {
        var payment = orderingCoffee.payOrder(id,
                new CreditCard(
                        request.cardHolderName(),
                        request.cardNumber(),
                        Month.of(request.expiryMonth()),
                        Year.of(request.expiryYear())
                ));
        return ResponseEntity.ok(PaymentResponse.fromDomain(payment));
    }
}
