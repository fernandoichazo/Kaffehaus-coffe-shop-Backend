package com.ucb.kaffehaus.inventario.producto.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductoDatasource {
    Producto save(Producto producto);
    Optional<Producto> update(UUID id, Producto producto);
    List<Producto> getAll();
    Optional<Producto> findOne(UUID id);
    boolean deleteOne(UUID id);
}
