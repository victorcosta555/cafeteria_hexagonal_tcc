package com.example.cafeteria.aplicacao.portas.saida;

import com.example.cafeteria.aplicacao.modelos.Pagamento;

import java.util.UUID;

public interface Pagamentos {

    Pagamento findPagamentoByPedidoId(UUID pedidoId);
    Pagamento save(Pagamento pagamento);
}
