package com.ucb.kaffehaus.personal.persona.application;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ucb.kaffehaus.personal.persona.domain.Persona;
import com.ucb.kaffehaus.personal.persona.domain.PersonaRepository;

@Service
@Transactional(readOnly = true)
public class GetAllPersonaUseCase {

    private final PersonaRepository personaRepository;

    public GetAllPersonaUseCase(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    public List<Persona> execute() {
        return this.personaRepository.getAll();
    }
}