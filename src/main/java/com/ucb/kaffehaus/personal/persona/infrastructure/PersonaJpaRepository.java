package com.ucb.kaffehaus.personal.persona.infrastructure;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaJpaRepository extends JpaRepository<PersonaEntity, Integer> {
    
}
