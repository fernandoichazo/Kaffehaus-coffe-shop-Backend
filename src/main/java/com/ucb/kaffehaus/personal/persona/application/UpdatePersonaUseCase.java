package com.ucb.kaffehaus.personal.persona.application;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ucb.kaffehaus.personal.persona.application.dto.UpdatePersonaRequest;
import com.ucb.kaffehaus.personal.persona.domain.Persona;
import com.ucb.kaffehaus.personal.persona.domain.PersonaRepository;
import com.ucb.kaffehaus.personal.shared.application.dto.ValidationResult;
import com.ucb.kaffehaus.personal.shared.application.exception.ValidationException;

@Service
@Transactional
public class UpdatePersonaUseCase {

    private final PersonaRepository personaRepository;

    public UpdatePersonaUseCase(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    public Optional<Persona> execute(int id, UpdatePersonaRequest request) {
        ValidationResult validationResult = request.validate();
        if (validationResult.hasErrors()) {
            throw new ValidationException(validationResult);
        }

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