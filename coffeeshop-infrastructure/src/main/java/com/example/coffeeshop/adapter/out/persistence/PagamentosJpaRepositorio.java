package com.example.coffeeshop.adapter.out.persistence;

import com.example.coffeeshop.adapter.out.persistence.entity.PagamentoEntidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PagamentosJpaRepositorio extends JpaRepository<PagamentoEntidade, UUID> {

    Optional<PagamentoEntidade> findByPedidoId(UUID pedidoId);
}
