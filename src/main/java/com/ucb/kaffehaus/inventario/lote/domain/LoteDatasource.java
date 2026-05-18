package com.ucb.kaffehaus.inventario.lote.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LoteDatasource {
    Lote save(Lote lote);
    Optional<Lote> update(UUID id, Lote lote);
    List<Lote> getAll();
    Optional<Lote> findOne(UUID id);
    boolean deleteOne(UUID id);
}
