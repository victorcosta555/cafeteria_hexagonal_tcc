package com.example.coffeeshop.adapter.out.persistence;

import com.example.coffeeshop.application.models.CartaoDeCredito;
import com.example.coffeeshop.application.models.Pagamento;
import com.example.coffeeshop.application.ports.out.Pagamentos;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.NoSuchElementException;
import java.util.UUID;

import static com.example.coffeeshop.application.textFitures.payment.CartaoDeCreditoTestFactory.aCreditCard;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@PersistenceTest
public class PagamentosJpaAdapterTest {

    @Autowired
    private Pagamentos pagamentos;

    @Test
    void creatingPaymentReturnsPersistedPayment() {
        var now = LocalDate.now();
        var creditCard = aCreditCard();
        var payment = new Pagamento(UUID.randomUUID(), creditCard, now);

        var persistedPayment = pagamentos.save(payment);

        assertThat(persistedPayment.cartaoDeCredito()).isEqualTo(creditCard);
        assertThat(persistedPayment.dataPagamento()).isEqualTo(now);
    }

    @Test
    @Sql("classpath:data/payment.sql")
    void findingPreviouslyPersistedPaymentReturnsDetails() {
        var payment = pagamentos.findPagamentoByPedidoId(UUID.fromString("a41c9394-3aa6-4484-b0b4-87de55fa2cf4"));

        var expectedCreditCard = new CartaoDeCredito("Michael Faraday", "11223344", Month.JANUARY, Year.of(2023));

        assertThat(payment.cartaoDeCredito()).isEqualTo(expectedCreditCard);
    }

    @Test
    void findingNonExistingPaymentThrowsException() {
        assertThatThrownBy(() -> pagamentos.findPagamentoByPedidoId(UUID.randomUUID())).isInstanceOf(NoSuchElementException.class);
    }
}
