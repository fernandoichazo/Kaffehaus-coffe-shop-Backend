package com.ucb.kaffehaus.personal.persona.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ucb.kaffehaus.personal.persona.domain.PersonaRepository;

@Service
@Transactional
public class DeletePersonaUseCase {

    private final PersonaRepository personaRepository;

    public DeletePersonaUseCase(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    public boolean execute(int id) {
        return this.personaRepository.deleteOne(id);
    }
}