package com.example.coffeeshop.adapter.in.rest.resource;

import com.example.coffeeshop.application.models.*;

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
