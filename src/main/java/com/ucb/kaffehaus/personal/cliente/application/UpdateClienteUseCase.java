package com.ucb.kaffehaus.personal.cliente.application;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ucb.kaffehaus.personal.cliente.application.dto.UpdateClienteRequest;
import com.ucb.kaffehaus.personal.cliente.domain.Cliente;
import com.ucb.kaffehaus.personal.cliente.domain.ClienteRepository;
import com.ucb.kaffehaus.personal.persona.domain.Persona;
import com.ucb.kaffehaus.personal.persona.domain.PersonaRepository;
import com.ucb.kaffehaus.shared.application.error.CustomException;

@Service
@Transactional
public class UpdateClienteUseCase {

    private final ClienteRepository clienteRepository;
    private final PersonaRepository personaRepository;

    public UpdateClienteUseCase(ClienteRepository clienteRepository, PersonaRepository personaRepository) {
        this.clienteRepository = clienteRepository;
        this.personaRepository = personaRepository;
    }

    public Optional<Cliente> execute(UUID id, UpdateClienteRequest request) {
        request.validate();

        Persona persona = null;
        if (request.getPersonaId() != null) {
            persona = this.personaRepository.findOne(request.getPersonaId())
                .orElseThrow(() -> CustomException.notFound("No se encontro persona con id " + request.getPersonaId()));
        }

        Cliente clienteToUpdate = Cliente.restore(id, persona, false);
        return this.clienteRepository.update(id, clienteToUpdate);
    }
}
