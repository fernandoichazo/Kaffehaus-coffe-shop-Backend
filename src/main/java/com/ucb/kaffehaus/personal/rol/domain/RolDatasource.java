package com.ucb.kaffehaus.personal.rol.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RolDatasource {
    Rol save(Rol rol);
    Optional<Rol> update(UUID Id, Rol rol);
    List<Rol> getAll();
    Optional<Rol> findOne(UUID Id);
    boolean deleteOne(UUID Id);
}
