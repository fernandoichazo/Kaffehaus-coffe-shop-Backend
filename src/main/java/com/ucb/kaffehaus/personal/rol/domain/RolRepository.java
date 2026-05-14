package com.ucb.kaffehaus.personal.rol.domain;

import java.util.List;
import java.util.Optional;

public interface RolRepository {
    Rol save(Rol rol);
    Optional<Rol> update(int Id, Rol rol);
    List<Rol> getAll();
    Optional<Rol> findOne(int Id);
    boolean deleteOne(int Id);
}
