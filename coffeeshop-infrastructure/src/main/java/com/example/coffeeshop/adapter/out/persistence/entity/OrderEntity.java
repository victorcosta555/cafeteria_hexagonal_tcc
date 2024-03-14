package com.example.coffeeshop.adapter.out.persistence.entity;

import com.example.coffeeshop.application.models.Location;
import com.example.coffeeshop.application.models.Order;
import com.example.coffeeshop.application.models.Status;
import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity {

    @Id
    private UUID id;

    @Enumerated
    @NotNull
    private Location location;

    @Enumerated
    @NotNull
    private Status status;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private List<LineItemEntity> items;

    public Order toDomain() {
        return new Order(
                id,
                location,
                items.stream().map(LineItemEntity::toDomain).toList(),
                status
        );
    }

    public static OrderEntity fromDomain(Order order) {
        return OrderEntity.builder()
                .id(order.getId())
                .location(order.getLocation())
                .status(order.getStatus())
                .items(order.getItems().stream().map(LineItemEntity::fromDomain).toList())
                .build();
    }
}
