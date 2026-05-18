package com.ucb.kaffehaus.inventario.receta.infrastructure;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RecetaJpaRepository extends JpaRepository<RecetaEntity, UUID> {

}
