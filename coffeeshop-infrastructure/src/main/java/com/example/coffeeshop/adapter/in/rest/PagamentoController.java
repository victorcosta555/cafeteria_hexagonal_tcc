package com.example.coffeeshop.adapter.in.rest;

import com.example.coffeeshop.adapter.in.rest.resource.PagamentoRequisicao;
import com.example.coffeeshop.adapter.in.rest.resource.PagamentoResposta;
import com.example.coffeeshop.application.models.CartaoDeCredito;
import com.example.coffeeshop.application.ports.in.PedidoCafe;
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
public class PagamentoController {

    private final PedidoCafe pedidoCafe;

    @PutMapping("/{id}")
    public ResponseEntity<PagamentoResposta> pagarPedido(@PathVariable UUID id, @Valid @RequestBody PagamentoRequisicao request) {
        var payment = pedidoCafe.pagarPedido(id,
                new CartaoDeCredito(
                        request.nomeUsuarioCartao(),
                        request.numeroCartao(),
                        Month.of(request.mesValidade()),
                        Year.of(request.anoValidade())
                ));
        return ResponseEntity.ok(PagamentoResposta.fromDomain(payment));
    }
}
