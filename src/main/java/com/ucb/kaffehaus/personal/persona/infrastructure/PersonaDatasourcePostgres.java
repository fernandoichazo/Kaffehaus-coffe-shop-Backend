package com.ucb.kaffehaus.personal.persona.infrastructure;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ucb.kaffehaus.personal.persona.domain.Persona;
import com.ucb.kaffehaus.personal.persona.domain.PersonaDatasource;
import com.ucb.kaffehaus.shared.application.error.CustomException;

@Service
public class PersonaDatasourcePostgres implements PersonaDatasource {

    PersonaJpaRepository personaJpaRepository;

    public PersonaDatasourcePostgres(PersonaJpaRepository personaJpaRepository) {
        this.personaJpaRepository = personaJpaRepository;
    }

    @Override
    public Persona save(Persona persona) {
        PersonaEntity personaDb = this.personaDomainToPersistence(persona);
        PersonaEntity personadb = this.personaJpaRepository.save(personaDb);
        persona.setId(personadb.getId());
        return persona;
    }

    @Override
    public Optional<Persona> update(int Id, Persona persona) {
        PersonaEntity personaEntity = this.personaJpaRepository.findById(Id)
                .orElseThrow(() -> CustomException.notFound("No se encontro persona con id " + Id));

        if (personaEntity.isBorrado()) {
            throw CustomException.notFound("No se encontro persona con id " + Id);
        }

        personaEntity.setNombre(persona.getNombre());
        personaEntity.setApellidos(persona.getApellidos());
        personaEntity.setTelefono(persona.getTelefono());
        personaEntity.setDni(persona.getDni());
        personaEntity.setBorrado(persona.isBorrado());

        PersonaEntity updatedPersona = this.personaJpaRepository.save(personaEntity);
        return Optional.of(this.toDomain(updatedPersona));
    }

    @Override
    public List<Persona> getAll() {
        return this.personaJpaRepository.findAll().stream()
                .filter(personaEntity -> !personaEntity.isBorrado())
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Persona> findOne(int Id) {
        PersonaEntity personaEntity = this.personaJpaRepository.findById(Id)
                .orElseThrow(() -> CustomException.notFound("No se encontro persona con id " + Id));

        if (personaEntity.isBorrado()) {
            throw CustomException.notFound("No se encontro persona con id " + Id);
        }

        return Optional.of(this.toDomain(personaEntity));
    }

    @Override
    public boolean deleteOne(int Id) {  
        PersonaEntity personaEntity = this.personaJpaRepository.findById(Id)
                .orElseThrow(() -> CustomException.notFound("No se encontro persona con id " + Id));

        if (personaEntity.isBorrado()) {
            throw CustomException.notFound("No se encontro persona con id " + Id);
        }

        personaEntity.setBorrado(true);
        this.personaJpaRepository.save(personaEntity);
        return true;
    }

    private Persona toDomain(PersonaEntity personaEntity) {
        return Persona.restore(
            personaEntity.getId(),
            personaEntity.getNombre(),
            personaEntity.getApellidos(),
            personaEntity.getTelefono(),
            personaEntity.getDni(),
            personaEntity.isBorrado()
        );
    }

    private PersonaEntity personaDomainToPersistence(Persona persona) {
        PersonaEntity entity = new PersonaEntity(
                persona.getId(),
                persona.getNombre(),
                persona.getApellidos(),
                persona.getTelefono(),
                persona.getDni(),
                persona.isBorrado());
        return entity;
    }
}
