package com.ucb.kaffehaus.personal.persona.infrastructure;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ucb.kaffehaus.personal.persona.domain.Persona;
import com.ucb.kaffehaus.personal.persona.domain.PersonaDatasource;

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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public List<Persona> getAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public Optional<Persona> findOne(int Id) {
        return personaJpaRepository.findById(Id).map(this::toDomain);
    }

    @Override
    public boolean deleteOne(int Id) {  
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteOne'");
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
