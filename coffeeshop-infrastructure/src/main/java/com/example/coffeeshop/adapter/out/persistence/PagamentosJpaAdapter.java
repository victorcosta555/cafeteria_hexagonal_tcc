package com.example.coffeeshop.adapter.out.persistence;

import com.example.coffeeshop.adapter.out.persistence.entity.PagamentoEntidade;
import com.example.coffeeshop.application.models.Pagamento;
import com.example.coffeeshop.application.ports.out.Pagamentos;
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
