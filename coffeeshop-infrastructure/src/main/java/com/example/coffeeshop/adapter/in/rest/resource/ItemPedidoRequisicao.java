package com.example.coffeeshop.adapter.in.rest.resource;

import com.example.coffeeshop.application.models.Bebida;
import com.example.coffeeshop.application.models.ItemPedido;
import com.example.coffeeshop.application.models.TipoLeite;
import com.example.coffeeshop.application.models.TamanhoBebida;

public record ItemPedidoRequisicao(Bebida bebida, TipoLeite tipoLeite, TamanhoBebida tamanhoBebida, Integer quantidade) {

    public ItemPedido toDomain() {
        return new ItemPedido(bebida, tipoLeite, tamanhoBebida, quantidade);
    }
}
