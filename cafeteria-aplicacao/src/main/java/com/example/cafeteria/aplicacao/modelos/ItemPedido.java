package com.example.cafeteria.aplicacao.modelos;

import java.math.BigDecimal;

public class ItemPedido {

    private Bebida bebida;
    private TipoLeite tipoLeite;
    private TamanhoBebida tamanhoBebida;
    private Integer quantidade;

    public ItemPedido() {
    }

    public ItemPedido(Bebida bebida, TipoLeite tipoLeite, TamanhoBebida tamanhoBebida, Integer quantidade) {
        this.bebida = bebida;
        this.tipoLeite = tipoLeite;
        this.tamanhoBebida = tamanhoBebida;
        this.quantidade = quantidade;
    }

    public Bebida getBebida() {
        return bebida;
    }

    public void setBebida(Bebida bebida) {
        this.bebida = bebida;
    }

    public TipoLeite getTipoLeite() {
        return tipoLeite;
    }

    public void setTipoLeite(TipoLeite tipoLeite) {
        this.tipoLeite = tipoLeite;
    }

    public TamanhoBebida getTamanhoBebida() {
        return tamanhoBebida;
    }

    public void setTamanhoBebida(TamanhoBebida tamanhoBebida) {
        this.tamanhoBebida = tamanhoBebida;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    BigDecimal getCusto() {
        var preco = BigDecimal.valueOf(4.0);
        if (this.tamanhoBebida == TamanhoBebida.GRANDE) {
            preco = preco.add(BigDecimal.ONE);
        }
        return preco.multiply(BigDecimal.valueOf(quantidade));
    }
};