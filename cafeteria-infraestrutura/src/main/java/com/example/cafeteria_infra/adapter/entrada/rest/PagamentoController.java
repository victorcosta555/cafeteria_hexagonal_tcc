package com.example.cafeteria_infra.adapter.entrada.rest;

import com.example.cafeteria_infra.adapter.entrada.rest.recursos.PagamentoRequisicao;
import com.example.cafeteria_infra.adapter.entrada.rest.recursos.PagamentoResposta;
import com.example.cafeteria.aplicacao.modelos.CartaoDeCredito;
import com.example.cafeteria.aplicacao.portas.entrada.PedidoCafe;
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
