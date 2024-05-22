package com.example.cafeteria_infra.adapter.entrada.rest.recursos;

import com.example.cafeteria.aplicacao.modelos.Bebida;
import com.example.cafeteria.aplicacao.modelos.ItemPedido;
import com.example.cafeteria.aplicacao.modelos.TipoLeite;
import com.example.cafeteria.aplicacao.modelos.TamanhoBebida;

public record ItemPedidoRequisicao(Bebida bebida, TipoLeite tipoLeite, TamanhoBebida tamanhoBebida, Integer quantidade) {

    public ItemPedido toDomain() {
        return new ItemPedido(bebida, tipoLeite, tamanhoBebida, quantidade);
    }
}
