package com.example.cafeteria.aplicacao.modelos;

import java.time.Month;
import java.time.Year;

public record CartaoDeCredito(String nomeUsuarioCartao, String numeroCartao, Month mesValidade, Year anoValidade) {}
