package com.example.cafeteria.aplicacao.modelos;

import java.time.Month;
import java.time.Year;

public class CartaoDeCredito {
    private String nomeUsuarioCartao;
    private String numeroCartao;
    private Month mesValidade;
    private Year anoValidade;

    public CartaoDeCredito() {
    }

    public CartaoDeCredito(String nomeUsuarioCartao, String numeroCartao, Month mesValidade, Year anoValidade) {
        this.nomeUsuarioCartao = nomeUsuarioCartao;
        this.numeroCartao = numeroCartao;
        this.mesValidade = mesValidade;
        this.anoValidade = anoValidade;
    }

    public String getNomeUsuarioCartao() {
        return nomeUsuarioCartao;
    }

    public void setNomeUsuarioCartao(String nomeUsuarioCartao) {
        this.nomeUsuarioCartao = nomeUsuarioCartao;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public Month getMesValidade() {
        return mesValidade;
    }

    public void setMesValidade(Month mesValidade) {
        this.mesValidade = mesValidade;
    }

    public Year getAnoValidade() {
        return anoValidade;
    }

    public void setAnoValidade(Year anoValidade) {
        this.anoValidade = anoValidade;
    }
}
