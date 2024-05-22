package com.example.coffeeshop.adapter.out.persistence.entity;

import com.example.coffeeshop.application.models.CartaoDeCredito;
import com.example.coffeeshop.application.models.Pagamento;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PagamentoEntidade {

    @Id
    @GeneratedValue
    private UUID id;

    @NotNull
    private UUID pedidoId;

    @NotNull
    private String nomeUsuarioCartao;

    @NotNull
    private String numeroCartao;

    @NotNull
    private Month mesValidade;

    @NotNull
    private Year anoValidade;

    @NotNull
    private LocalDate dataPagamento;

    public Pagamento toDomain() {
        return new Pagamento(
                pedidoId,
                new CartaoDeCredito(
                        nomeUsuarioCartao,
                        numeroCartao,
                        mesValidade,
                        anoValidade
                ),
                dataPagamento
        );
    }

    public static PagamentoEntidade fromDomain(Pagamento pagamento) {
        return PagamentoEntidade.builder()
                .pedidoId(pagamento.pedidoId())
                .nomeUsuarioCartao(pagamento.cartaoDeCredito().nomeUsuarioCartao())
                .numeroCartao(pagamento.cartaoDeCredito().numeroCartao())
                .mesValidade(pagamento.cartaoDeCredito().mesValidade())
                .anoValidade(pagamento.cartaoDeCredito().anoValidade())
                .dataPagamento(pagamento.dataPagamento())
                .build();
    }
}
