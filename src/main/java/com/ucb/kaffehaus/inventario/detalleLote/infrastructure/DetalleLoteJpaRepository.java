package com.ucb.kaffehaus.inventario.detalleLote.infrastructure;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleLoteJpaRepository extends JpaRepository<DetalleLoteEntity, UUID> {

}
