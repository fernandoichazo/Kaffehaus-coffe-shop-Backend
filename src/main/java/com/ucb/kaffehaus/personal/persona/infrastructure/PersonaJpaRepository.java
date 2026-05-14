package com.ucb.kaffehaus.personal.persona.infrastructure;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaJpaRepository extends JpaRepository<PersonaEntity, UUID> {
    
}
