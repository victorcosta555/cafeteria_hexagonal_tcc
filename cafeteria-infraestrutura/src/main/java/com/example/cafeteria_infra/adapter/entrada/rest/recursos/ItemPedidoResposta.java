package com.example.cafeteria_infra.adapter.entrada.rest.recursos;

import com.example.cafeteria.aplicacao.modelos.Bebida;
import com.example.cafeteria.aplicacao.modelos.ItemPedido;
import com.example.cafeteria.aplicacao.modelos.TamanhoBebida;
import com.example.cafeteria.aplicacao.modelos.TipoLeite;

public record ItemPedidoResposta(Bebida bebida, TipoLeite tipoLeite, TamanhoBebida tamanhoBebida, Integer quantity) {

    public static ItemPedidoResposta fromDomain(ItemPedido itemPedido) {
        return new ItemPedidoResposta(
                itemPedido.bebida(),
                itemPedido.tipoLeite(),
                itemPedido.tamanhoBebida(),
                itemPedido.quantidade()
        );
    }
}
