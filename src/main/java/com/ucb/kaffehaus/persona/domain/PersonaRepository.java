package com.ucb.kaffehaus.persona.domain;

import java.util.List;
import java.util.Optional;

public interface PersonaRepository {
    Persona save(Persona persona);
    Optional<Persona> update(int Id, Persona persona);
    List<Persona> getAll();
    Optional<Persona> findOne(int Id);
    boolean deleteOne(int Id);
} 