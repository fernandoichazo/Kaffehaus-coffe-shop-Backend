package com.ucb.kaffehaus.personal.persona.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ucb.kaffehaus.personal.persona.application.dto.CreatePersonaRequest;
import com.ucb.kaffehaus.personal.persona.domain.Persona;
import com.ucb.kaffehaus.personal.persona.domain.PersonaRepository;
import com.ucb.kaffehaus.shared.application.dto.ValidationResult;
import com.ucb.kaffehaus.shared.application.exception.ValidationException;

@Service
@Transactional
public class CreatePersonaUseCase {

    private final PersonaRepository personaRepository;

    public CreatePersonaUseCase(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    public Persona execute(CreatePersonaRequest request) {

        ValidationResult validationResult = request.validate();
        if (validationResult.hasErrors()) {
            throw new ValidationException(validationResult);
        }

        Persona persona = Persona.create(
                request.getNombre(),
                request.getApellidos(),
                request.getTelefono(),
                request.getDni());
                
        return this.personaRepository.save(persona);
    }
}
