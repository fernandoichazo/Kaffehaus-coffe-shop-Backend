package com.ucb.kaffehaus.personal.persona.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonaDatasource {
    Persona save(Persona persona);
    Optional<Persona> update(UUID Id, Persona persona);
    List<Persona> getAll();
    Optional<Persona> findOne(UUID Id);
    boolean deleteOne(UUID Id);
}
