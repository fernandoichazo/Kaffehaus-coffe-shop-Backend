package com.ucb.kaffehaus.personal.persona.application;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ucb.kaffehaus.personal.persona.application.dto.UpdatePersonaRequest;
import com.ucb.kaffehaus.personal.persona.domain.Persona;
import com.ucb.kaffehaus.personal.persona.domain.PersonaRepository;

@Service
@Transactional
public class UpdatePersonaUseCase {

    private final PersonaRepository personaRepository;

    public UpdatePersonaUseCase(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    public Optional<Persona> execute(UUID id, UpdatePersonaRequest request) {
        request.validate();

        Persona personaToUpdate = Persona.create(
                request.getNombre(),
                request.getApellidos(),
                request.getTelefono(),
                request.getDni());
        personaToUpdate.setId(id);
        personaToUpdate.setBorrado(false);

        return this.personaRepository.update(id, personaToUpdate);
    }
}