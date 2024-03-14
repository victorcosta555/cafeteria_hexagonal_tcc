package com.example.coffeeshop.adapter.out.persistence.entity;

import com.example.coffeeshop.application.models.Drink;
import com.example.coffeeshop.application.models.LineItem;
import com.example.coffeeshop.application.models.Milk;
import com.example.coffeeshop.application.models.Size;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LineItemEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Enumerated
    @NotNull
    private Drink drink;

    @Enumerated
    @NotNull
    private Size size;

    @Enumerated
    @NotNull
    private Milk milk;

    @NotNull
    private Integer quantity;

    public LineItem toDomain() {
        return new LineItem(
                drink,
                milk,
                size,
                quantity
        );
    }

    public static LineItemEntity fromDomain(LineItem lineItem) {
        return LineItemEntity.builder()
                .drink(lineItem.drink())
                .quantity(lineItem.quantity())
                .milk(lineItem.milk())
                .size(lineItem.size())
                .build();
    }
}
