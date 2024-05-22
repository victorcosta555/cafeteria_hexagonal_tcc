package com.example.cafeteria.aplicacao.textFitures.out.stub;

import com.example.cafeteria.aplicacao.modelos.Pagamento;
import com.example.cafeteria.aplicacao.portas.saida.Pagamentos;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PagamentosEmMemoria implements Pagamentos {

    private final Map<UUID, Pagamento> entities = new HashMap<>();

    @Override
    public Pagamento findPagamentoByPedidoId(UUID pedidoId) {
        return entities.get(pedidoId);
    }

    @Override
    public Pagamento save(Pagamento pagamento) {
       entities.put(pagamento.pedidoId(), pagamento);
       return pagamento;
    }
}
