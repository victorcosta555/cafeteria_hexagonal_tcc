package com.example.coffeeshop.adapter.out.persistence;

import com.example.coffeeshop.adapter.out.persistence.entity.PedidoEntidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PedidosJpaRepositorio extends JpaRepository<PedidoEntidade, UUID> {
}
