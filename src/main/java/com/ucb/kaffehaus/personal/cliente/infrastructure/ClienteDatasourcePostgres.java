package com.ucb.kaffehaus.personal.cliente.infrastructure;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ucb.kaffehaus.personal.cliente.domain.Cliente;
import com.ucb.kaffehaus.personal.cliente.domain.ClienteDatasource;
import com.ucb.kaffehaus.personal.persona.domain.Persona;
import com.ucb.kaffehaus.personal.persona.infrastructure.PersonaEntity;
import com.ucb.kaffehaus.personal.persona.infrastructure.PersonaJpaRepository;
import com.ucb.kaffehaus.shared.application.error.CustomException;

@Service
public class ClienteDatasourcePostgres implements ClienteDatasource{

    private final ClienteJpaRepository clienteJpaRepository;
    private final PersonaJpaRepository personaJpaRepository;

    public ClienteDatasourcePostgres(
            ClienteJpaRepository clienteJpaRepository,
            PersonaJpaRepository personaJpaRepository) {
        this.clienteJpaRepository = clienteJpaRepository;
        this.personaJpaRepository = personaJpaRepository;
    }

    @Override
    public Cliente save(Cliente cliente) {
        PersonaEntity personaEntity = this.getPersonaEntity(cliente.getPersona().getId());
        ClienteEntity entity = new ClienteEntity(personaEntity);
        entity.setBorrado(cliente.isBorrado());
        ClienteEntity saved = this.clienteJpaRepository.save(entity);
        cliente.setId(saved.getId());
        return cliente;
    }

    @Override
    public Optional<Cliente> update(int Id, Cliente cliente) {
        ClienteEntity entity = this.clienteJpaRepository.findById(Id)
                .orElseThrow(() -> CustomException.notFound("No se encontro cliente con id " + Id));

        if (entity.isBorrado()) {
            throw CustomException.notFound("No se encontro cliente con id " + Id);
        }

        if (cliente.getPersona() != null && cliente.getPersona().getId() != null) {
            PersonaEntity personaEntity = this.getPersonaEntity(cliente.getPersona().getId());
            entity.setPersona(personaEntity);
        }
        entity.setBorrado(cliente.isBorrado());

        ClienteEntity updated = this.clienteJpaRepository.save(entity);
        return Optional.of(this.toDomain(updated));
    }

    @Override
    public List<Cliente> getAll() {
        return this.clienteJpaRepository.findAll().stream()
                .filter(entity -> !entity.isBorrado())
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Cliente> findOne(int Id) {
        ClienteEntity entity = this.clienteJpaRepository.findById(Id)
                .orElseThrow(() -> CustomException.notFound("No se encontro cliente con id " + Id));

        if (entity.isBorrado()) {
            throw CustomException.notFound("No se encontro cliente con id " + Id);
        }

        return Optional.of(this.toDomain(entity));
    }

    @Override
    public boolean deleteOne(int Id) {
        ClienteEntity entity = this.clienteJpaRepository.findById(Id)
                .orElseThrow(() -> CustomException.notFound("No se encontro cliente con id " + Id));

        if (entity.isBorrado()) {
            throw CustomException.notFound("No se encontro cliente con id " + Id);
        }

        entity.setBorrado(true);
        this.clienteJpaRepository.save(entity);
        return true;
    }

    private Cliente toDomain(ClienteEntity entity) {
        PersonaEntity personaEntity = entity.getPersona();
        Persona persona = Persona.restore(
                personaEntity.getId(),
                personaEntity.getNombre(),
                personaEntity.getApellidos(),
                personaEntity.getTelefono(),
                personaEntity.getDni(),
                personaEntity.isBorrado());

        return Cliente.restore(entity.getId(), persona, entity.isBorrado());
    }

    private PersonaEntity getPersonaEntity(Integer personaId) {
        PersonaEntity personaEntity = this.personaJpaRepository.findById(personaId)
                .orElseThrow(() -> CustomException.notFound("No se encontro persona con id " + personaId));

        if (personaEntity.isBorrado()) {
            throw CustomException.notFound("No se encontro persona con id " + personaId);
        }

        return personaEntity;
    }

}
