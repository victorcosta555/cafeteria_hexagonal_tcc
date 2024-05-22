package com.example.cafeteria_infra.adapter.saida.persistencia;

import com.example.cafeteria_infra.adapter.saida.persistencia.entidades.PedidoEntidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PedidosJpaRepositorio extends JpaRepository<PedidoEntidade, UUID> {
}
