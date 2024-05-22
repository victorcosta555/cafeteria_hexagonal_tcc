package com.example.coffeeshop.application.models;

import java.time.Month;
import java.time.Year;

public record CartaoDeCredito(String nomeUsuarioCartao, String numeroCartao, Month mesValidade, Year anoValidade) {}
