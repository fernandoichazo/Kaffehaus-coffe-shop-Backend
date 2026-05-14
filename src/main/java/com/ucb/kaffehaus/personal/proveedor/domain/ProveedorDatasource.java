package com.ucb.kaffehaus.personal.proveedor.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProveedorDatasource {
    Proveedor save(Proveedor proveedor);
    Optional<Proveedor> update(UUID Id, Proveedor proveedor);
    List<Proveedor> getAll();
    Optional<Proveedor> findOne(UUID Id);
    boolean deleteOne(UUID Id);
}
