package com.ucb.kaffehaus.personal.proveedor.domain;

import java.util.List;
import java.util.Optional;

public interface ProveedorRepository {
    Proveedor save(Proveedor proveedor);
    Optional<Proveedor> update(int Id, Proveedor proveedor);
    List<Proveedor> getAll();
    Optional<Proveedor> findOne(int Id);
    boolean deleteOne(int Id);
}
