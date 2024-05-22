package com.example.cafeteria.aplicacao.modelos;

import java.math.BigDecimal;

public record ItemPedido(Bebida bebida, TipoLeite tipoLeite, TamanhoBebida tamanhoBebida, Integer quantidade){

    BigDecimal getCusto() {
        var preco = BigDecimal.valueOf(4.0);
        if (this.tamanhoBebida == TamanhoBebida.GRANDE) {
            preco = preco.add(BigDecimal.ONE);
        }
        return preco.multiply(BigDecimal.valueOf(quantidade));
    }
};