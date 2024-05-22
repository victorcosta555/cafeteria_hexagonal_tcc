package com.example.coffeeshop.application.textFitures.out.stub;

import com.example.coffeeshop.application.models.Pagamento;
import com.example.coffeeshop.application.ports.out.Pagamentos;

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
