package com.example.cafeteria_infra.adapter.saida.persistencia;

import com.example.cafeteria.aplicacao.modelos.*;
import com.example.cafeteria.aplicacao.portas.saida.PedidoNaoEncontrado;
import com.example.cafeteria.aplicacao.portas.saida.Pedidos;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@PersistenceTest
public class PedidosJpaAdapterTest {

    @Autowired
    private Pedidos pedidos;

    @Autowired
    private PedidosJpaRepositorio pedidosJpaRepositorio;


    @Test
    void creatingOrderReturnsPersistedOrder() {
        var item = new ItemPedido(Bebida.LATTE, TipoLeite.INTEGRAL, TamanhoBebida.PEQUENO, 1);
        var order = new Pedido(LocalConsumoPedido.VIAGEM, List.of(
                item
        ));

        var persistetdOrder = pedidos.savePedido(order);

        assertThat(persistetdOrder.getLocalConsumoPedido()).isEqualTo(LocalConsumoPedido.VIAGEM);
        assertThat(persistetdOrder.getItems()).containsExactly(item);
    }

    @Test
    @Sql("classpath:data/order.sql")
    void findingPreviouslyPersistedOrderReturnsDetails() {
        var order = pedidos.findPedidoById(UUID.fromString("757d9c0f-400f-4137-9aea-83e64ba3efb2"));

        assertThat(order.getLocalConsumoPedido()).isEqualTo(LocalConsumoPedido.LOJA);
        assertThat(order.getItems()).containsExactly(new ItemPedido(Bebida.ESPRESSO, TipoLeite.DESNATADO, TamanhoBebida.GRANDE, 1));
    }

    @Test
    void findingNonExistingOrderThrowsException() {
        assertThatThrownBy(() -> pedidos.findPedidoById(UUID.randomUUID())).isInstanceOf(PedidoNaoEncontrado.class);
    }

    @Test
    @Sql("classpath:data/order.sql")
    void deletesPreviouslyPersistedOrder() {
        pedidos.deleteById(UUID.fromString("757d9c0f-400f-4137-9aea-83e64ba3efb2"));

        Assertions.assertThat(pedidosJpaRepositorio.findById(UUID.fromString("757d9c0f-400f-4137-9aea-83e64ba3efb2"))).isEmpty();
    }

}
