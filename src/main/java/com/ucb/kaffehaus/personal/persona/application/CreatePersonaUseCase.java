package com.ucb.kaffehaus.personal.persona.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ucb.kaffehaus.personal.persona.application.dto.CreatePersonaRequest;
import com.ucb.kaffehaus.personal.persona.domain.Persona;
import com.ucb.kaffehaus.personal.persona.domain.PersonaRepository;

@Service
@Transactional
public class CreatePersonaUseCase {

    private final PersonaRepository personaRepository;

    public CreatePersonaUseCase(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    public Persona execute(CreatePersonaRequest request) {
        Persona persona = Persona.create(
                request.getNombre(),
                request.getApellidos(),
                request.getTelefono(),
                request.getDni());
        return this.personaRepository.save(persona);
    }
}
