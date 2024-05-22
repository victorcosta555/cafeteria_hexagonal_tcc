package com.example.coffeeshop.adapter.out.persistence;

import com.example.coffeeshop.adapter.out.persistence.entity.PedidoEntidade;
import com.example.coffeeshop.application.models.Pedido;
import com.example.coffeeshop.application.ports.out.PedidoNaoEncontrado;
import com.example.coffeeshop.application.ports.out.Pedidos;
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
