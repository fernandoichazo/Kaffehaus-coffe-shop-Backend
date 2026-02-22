package com.ucb.kaffehaus.persona.infrastructure;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ucb.kaffehaus.persona.domain.Persona;
import com.ucb.kaffehaus.persona.domain.PersonaDatasource;

@Service
public class PersonaDatasourcePostgres implements PersonaDatasource{

    PersonaJpaRepository personaJpaRepository;

    public PersonaDatasourcePostgres(PersonaJpaRepository personaJpaRepository) {
        this.personaJpaRepository = personaJpaRepository;
    }


    public PersonaEntity personaDomainToPersistence(Persona persona){
        PersonaEntity entity = new PersonaEntity(
            persona.getId(),
            persona.getNombre(),
            persona.getApellidos(),
            persona.getTelefono(),
            persona.getDni(),
            persona.isBorrado()    
        );
        return entity;
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findOne'");
    }

    @Override
    public boolean deleteOne(int Id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteOne'");
    }
}
