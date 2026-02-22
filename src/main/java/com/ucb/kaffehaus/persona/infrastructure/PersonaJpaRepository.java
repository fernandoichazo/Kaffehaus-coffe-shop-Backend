package com.ucb.kaffehaus.persona.infrastructure;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaJpaRepository extends JpaRepository<PersonaEntity, Integer> {
    
}
