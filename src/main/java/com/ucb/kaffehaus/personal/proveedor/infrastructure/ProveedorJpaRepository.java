package com.ucb.kaffehaus.personal.proveedor.infrastructure;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProveedorJpaRepository extends JpaRepository<ProveedorEntity, UUID> {

}
