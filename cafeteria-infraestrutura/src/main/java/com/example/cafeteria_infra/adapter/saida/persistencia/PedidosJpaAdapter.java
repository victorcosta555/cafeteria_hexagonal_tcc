package com.example.cafeteria_infra.adapter.saida.persistencia;

import com.example.cafeteria_infra.adapter.saida.persistencia.entidades.PedidoEntidade;
import com.example.cafeteria.aplicacao.modelos.Pedido;
import com.example.cafeteria.aplicacao.portas.saida.PedidoNaoEncontrado;
import com.example.cafeteria.aplicacao.portas.saida.Pedidos;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PedidosJpaAdapter implements Pedidos {

    private final PedidosJpaRepositorio pedidosJpaRepositorio;

    @Override
    public Pedido findPedidoById(UUID pedidoId) throws PedidoNaoEncontrado {
        return pedidosJpaRepositorio.findById(pedidoId)
                .map(PedidoEntidade::toDomain)
                .orElseThrow(PedidoNaoEncontrado::new);
    }

    @Override
    public Pedido savePedido(Pedido pedido) {
        return pedidosJpaRepositorio.save(PedidoEntidade.fromDomain(pedido)).toDomain();
    }

    @Override
    public void deleteById(UUID pedidoId) {
        pedidosJpaRepositorio.deleteById(pedidoId);
    }
}
