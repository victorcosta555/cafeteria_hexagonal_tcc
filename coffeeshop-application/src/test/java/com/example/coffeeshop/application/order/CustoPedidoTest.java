package com.example.coffeeshop.application.order;

import com.example.coffeeshop.application.models.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class CustoPedidoTest {

    private static Stream<Arguments> drinkCosts() {
        return Stream.of(
                arguments(1, TamanhoBebida.PEQUENO, BigDecimal.valueOf(4.0)),
                arguments(1, TamanhoBebida.GRANDE, BigDecimal.valueOf(5.0)),
                arguments(2, TamanhoBebida.PEQUENO, BigDecimal.valueOf(8.0))
        );
    }

    @ParameterizedTest(name = "{0} drinks of size {1} cost {2}")
    @MethodSource("drinkCosts")
    public void orderCostBasedOnQuantityAndSize(int quantity, TamanhoBebida tamanhoBebida, BigDecimal expectedCost) {
        var order = new Pedido(LocalConsumoPedido.VIAGEM, List.of(
                new ItemPedido(Bebida.LATTE, TipoLeite.INTEGRAL, tamanhoBebida, quantity)
        ));

        assertThat(order.getCusto()).isEqualTo(expectedCost);
    }

    @Test
    void orderCostIsSumOfLineItemCosts() {
        var order = new Pedido(LocalConsumoPedido.VIAGEM, List.of(
                new ItemPedido(Bebida.LATTE, TipoLeite.DESNATADO, TamanhoBebida.GRANDE, 1),
                new ItemPedido(Bebida.ESPRESSO, TipoLeite.SOJA, TamanhoBebida.PEQUENO, 1)
        ));

        assertThat(order.getCusto()).isEqualTo(BigDecimal.valueOf(9.0));
    }
}
