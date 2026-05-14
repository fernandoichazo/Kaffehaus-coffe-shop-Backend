package com.ucb.kaffehaus.personal.cliente.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ucb.kaffehaus.personal.cliente.application.dto.CreateClienteRequest;
import com.ucb.kaffehaus.personal.cliente.domain.Cliente;
import com.ucb.kaffehaus.personal.cliente.domain.ClienteRepository;
import com.ucb.kaffehaus.personal.persona.domain.Persona;
import com.ucb.kaffehaus.personal.persona.domain.PersonaRepository;
import com.ucb.kaffehaus.shared.application.error.CustomException;

@Service
@Transactional
public class CreateClienteUseCase {

    private final ClienteRepository clienteRepository;
    private final PersonaRepository personaRepository;

    public CreateClienteUseCase(ClienteRepository clienteRepository, PersonaRepository personaRepository) {
        this.clienteRepository = clienteRepository;
        this.personaRepository = personaRepository;
    }

    public Cliente execute(CreateClienteRequest request) {
        request.validate();
        Persona persona;
        if (request.getPersonaId() == null) {
            persona = Persona.create(
                    request.getNombre(),
                    request.getApellidos(),
                    request.getTelefono(),
                    request.getDni());
            persona = this.personaRepository.save(persona);
        } else {
            persona = this.personaRepository.findOne(request.getPersonaId())
                    .orElseThrow(() -> CustomException.notFound("No se encontro persona con id " + request.getPersonaId()));
        }

        Cliente cliente = Cliente.create(persona);
        return this.clienteRepository.save(cliente);
    }
}
