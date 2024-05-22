package com.example.coffeeshop.adapter.in.rest;

import com.example.coffeeshop.adapter.in.rest.resource.PedidoRequisicao;
import com.example.coffeeshop.adapter.in.rest.resource.OrderResponse;
import com.example.coffeeshop.application.ports.in.PedidoCafe;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoCafe pedidoCafe;

    @PostMapping
    public ResponseEntity<OrderResponse> criarPedido(@RequestBody PedidoRequisicao request, UriComponentsBuilder uriComponentsBuilder) {
        var order = pedidoCafe.fazerPedido(request.toDomain());
        var location = uriComponentsBuilder.path("/order/{id}")
                .buildAndExpand(order.getId())
                .toUri();
        return ResponseEntity.created(location).body(OrderResponse.fromDomain(order));
    }

    @PutMapping("{id}")
    public ResponseEntity<OrderResponse> atualizarPedido(@PathVariable UUID id, @RequestBody PedidoRequisicao request) {
        var order = pedidoCafe.atualizarPedido(id, request.toDomain());
        return ResponseEntity.ok(OrderResponse.fromDomain(order));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> cancelarPedido(@PathVariable UUID id) {
        pedidoCafe.cancelarPedido(id);
        return ResponseEntity.noContent().build();
    }
}
