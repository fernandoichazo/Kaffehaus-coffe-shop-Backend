package com.ucb.kaffehaus.persona.infrastructure;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.ucb.kaffehaus.persona.domain.Persona;
import com.ucb.kaffehaus.persona.domain.PersonaDatasource;
import com.ucb.kaffehaus.persona.domain.PersonaRepository;

@Service
public class PersonaRepositoryImpl implements PersonaRepository{

    private final PersonaDatasource personaDatasource;

    PersonaRepositoryImpl(PersonaDatasource personaDatasource){
        this.personaDatasource = personaDatasource;
    }

    @Override
    public Persona save(Persona persona) {
        return this.personaDatasource.save(persona);
    }

    @Override
    public Optional<Persona> update(int Id, Persona persona) {
        return this.personaDatasource.update(Id, persona);
    }

    @Override
    public List<Persona> getAll() {
        return this.personaDatasource.getAll();
    }

    @Override
    public Optional<Persona> findOne(int Id) {
        return this.personaDatasource.findOne(Id);
    }

    @Override
    public boolean deleteOne(int Id) {
        return this.personaDatasource.deleteOne(Id);
    }
    
    

}
