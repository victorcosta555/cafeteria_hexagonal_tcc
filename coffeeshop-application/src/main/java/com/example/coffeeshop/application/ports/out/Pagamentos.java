package com.example.coffeeshop.application.ports.out;

import com.example.coffeeshop.application.models.Pagamento;

import java.util.UUID;

public interface Pagamentos {

    Pagamento findPagamentoByPedidoId(UUID pedidoId);
    Pagamento save(Pagamento pagamento);
}
