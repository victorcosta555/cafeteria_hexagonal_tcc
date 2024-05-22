package com.example.cafeteria_infra.adapter.entrada.rest;

import com.example.cafeteria_infra.adapter.entrada.rest.recursos.OrderResponse;
import com.example.cafeteria_infra.adapter.entrada.rest.recursos.ReceiptResponse;
import com.example.cafeteria.aplicacao.portas.entrada.PedidoCafe;
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
