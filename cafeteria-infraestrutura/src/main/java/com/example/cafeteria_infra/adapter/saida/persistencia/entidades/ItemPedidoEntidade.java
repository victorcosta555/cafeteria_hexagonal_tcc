package com.example.cafeteria_infra.adapter.saida.persistencia.entidades;

import com.example.cafeteria.aplicacao.modelos.Bebida;
import com.example.cafeteria.aplicacao.modelos.ItemPedido;
import com.example.cafeteria.aplicacao.modelos.TamanhoBebida;
import com.example.cafeteria.aplicacao.modelos.TipoLeite;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemPedidoEntidade {

    @Id
    @GeneratedValue
    private UUID id;

    @Enumerated
    @NotNull
    private Bebida bebida;

    @Enumerated
    @NotNull
    private TamanhoBebida tamanhoBebida;

    @Enumerated
    @NotNull
    private TipoLeite tipoLeite;

    @NotNull
    private Integer quantidade;

    public ItemPedido toDomain() {
        return new ItemPedido(
                bebida,
                tipoLeite,
                tamanhoBebida,
                quantidade
        );
    }

    public static ItemPedidoEntidade fromDomain(ItemPedido itemPedido) {
        return ItemPedidoEntidade.builder()
                .bebida(itemPedido.bebida())
                .quantidade(itemPedido.quantidade())
                .tipoLeite(itemPedido.tipoLeite())
                .tamanhoBebida(itemPedido.tamanhoBebida())
                .build();
    }
}
