package com.ucb.kaffehaus.personal.entidad.infrastructure;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EntidadJpaRepository extends JpaRepository<EntidadEntity, UUID> {

}
