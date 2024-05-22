package com.example.cafeteria_infra.adapter.saida.persistencia.entidades;

import com.example.cafeteria.aplicacao.modelos.LocalConsumoPedido;
import com.example.cafeteria.aplicacao.modelos.Pedido;
import com.example.cafeteria.aplicacao.modelos.Status;
import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PedidoEntidade {

    @Id
    private UUID id;

    @Enumerated
    @NotNull
    private LocalConsumoPedido localConsumoPedido;

    @Enumerated
    @NotNull
    private Status status;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "pedido_id")
    private List<ItemPedidoEntidade> items;

    public Pedido toDomain() {
        return new Pedido(
                id,
                localConsumoPedido,
                items.stream().map(ItemPedidoEntidade::toDomain).toList(),
                status
        );
    }

    public static PedidoEntidade fromDomain(Pedido pedido) {
        return PedidoEntidade.builder()
                .id(pedido.getId())
                .localConsumoPedido(pedido.getLocalConsumoPedido())
                .status(pedido.getStatus())
                .items(pedido.getItems().stream().map(ItemPedidoEntidade::fromDomain).toList())
                .build();
    }
}
