package com.ucb.kaffehaus.inventario.receta.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RecetaDatasource {
    Receta save(Receta receta);
    Optional<Receta> update(UUID id, Receta receta);
    List<Receta> getAll();
    Optional<Receta> findOne(UUID id);
    boolean deleteOne(UUID id);
}
