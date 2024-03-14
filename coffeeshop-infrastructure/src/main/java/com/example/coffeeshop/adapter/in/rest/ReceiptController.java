package com.example.coffeeshop.adapter.in.rest;

import com.example.coffeeshop.adapter.in.rest.resource.OrderResponse;
import com.example.coffeeshop.adapter.in.rest.resource.ReceiptResponse;
import com.example.coffeeshop.application.ports.in.OrderingCoffee;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/receipt")
@RequiredArgsConstructor
public class ReceiptController {

    private final OrderingCoffee orderingCoffee;

    @GetMapping("/{id}")
    public ResponseEntity<ReceiptResponse> readReceipt(@PathVariable UUID id) {
        var receipt = orderingCoffee.readReceipt(id);
        return ResponseEntity.ok(ReceiptResponse.fromDomain(receipt));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OrderResponse> completeOrder(@PathVariable UUID id) {
        var order = orderingCoffee.takeOrder(id);
        return ResponseEntity.ok(OrderResponse.fromDomain(order));
    }
}
