package com.example.cafeteria_infra.adapter.saida.persistencia;

import com.example.cafeteria_infra.adapter.saida.persistencia.entidades.PagamentoEntidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PagamentosJpaRepositorio extends JpaRepository<PagamentoEntidade, UUID> {

    Optional<PagamentoEntidade> findByPedidoId(UUID pedidoId);
}
