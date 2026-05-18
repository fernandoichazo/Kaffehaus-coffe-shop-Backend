package com.ucb.kaffehaus.inventario.detalleLote.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DetalleLoteRepository {
    DetalleLote save(DetalleLote detalleLote);
    Optional<DetalleLote> update(UUID id, DetalleLote detalleLote);
    List<DetalleLote> getAll();
    Optional<DetalleLote> findOne(UUID id);
    boolean deleteOne(UUID id);
}
