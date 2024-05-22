package com.example.cafeteria_infra.adapter.saida.persistencia;

import com.example.cafeteria_infra.adapter.saida.persistencia.entidades.PagamentoEntidade;
import com.example.cafeteria.aplicacao.modelos.Pagamento;
import com.example.cafeteria.aplicacao.portas.saida.Pagamentos;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PagamentosJpaAdapter implements Pagamentos {

    private final PagamentosJpaRepositorio pagamentosJpaRepositorio;

    @Override
    public Pagamento findPagamentoByPedidoId(UUID pedidoId) {
        return pagamentosJpaRepositorio.findByPedidoId(pedidoId)
                .map(PagamentoEntidade::toDomain)
                .orElseThrow();
    }

    @Override
    public Pagamento save(Pagamento pagamento) {
        return pagamentosJpaRepositorio.save(PagamentoEntidade.fromDomain(pagamento)).toDomain();
    }
}
