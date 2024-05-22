package com.example.coffeeshop.adapter.in.rest;

import com.example.coffeeshop.adapter.in.rest.resource.OrderResponse;
import com.example.coffeeshop.adapter.in.rest.resource.ReceiptResponse;
import com.example.coffeeshop.application.ports.in.PedidoCafe;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/receipt")
@RequiredArgsConstructor
public class ReciboController {

    private final PedidoCafe pedidoCafe;

    @GetMapping("/{id}")
    public ResponseEntity<ReceiptResponse> lerReciboPedido(@PathVariable UUID id) {
        var receipt = pedidoCafe.lerRecibo(id);
        return ResponseEntity.ok(ReceiptResponse.fromDomain(receipt));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OrderResponse> completarPedido(@PathVariable UUID id) {
        var order = pedidoCafe.entregarPedido(id);
        return ResponseEntity.ok(OrderResponse.fromDomain(order));
    }
}
