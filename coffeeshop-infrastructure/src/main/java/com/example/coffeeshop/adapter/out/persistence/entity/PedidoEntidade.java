package com.example.coffeeshop.adapter.out.persistence.entity;

import com.example.coffeeshop.application.models.LocalConsumoPedido;
import com.example.coffeeshop.application.models.Pedido;
import com.example.coffeeshop.application.models.Status;
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
