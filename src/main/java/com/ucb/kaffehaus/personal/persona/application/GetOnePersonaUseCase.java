package com.ucb.kaffehaus.personal.persona.application;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ucb.kaffehaus.personal.persona.domain.Persona;
import com.ucb.kaffehaus.personal.persona.domain.PersonaRepository;

@Service
@Transactional(readOnly = true)
public class GetOnePersonaUseCase {

    private final PersonaRepository personaRepository;

    public GetOnePersonaUseCase(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    public Optional<Persona> execute(int id) {
        return this.personaRepository.findOne(id)
                .filter(persona -> !persona.isBorrado());
    }
}