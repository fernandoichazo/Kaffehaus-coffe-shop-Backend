package com.ucb.kaffehaus.inventario.lote.infrastructure;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LoteJpaRepository extends JpaRepository<LoteEntity, UUID> {

}
