package com.ucb.kaffehaus.inventario.categoria.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoriaRepository {
    Categoria save(Categoria categoria);
    Optional<Categoria> update(UUID id, Categoria categoria);
    List<Categoria> getAll();
    Optional<Categoria> findOne(UUID id);
    boolean deleteOne(UUID id);
}
