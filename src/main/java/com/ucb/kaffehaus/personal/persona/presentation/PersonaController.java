package com.ucb.kaffehaus.personal.persona.presentation;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ucb.kaffehaus.personal.persona.application.CreatePersonaUseCase;
import com.ucb.kaffehaus.personal.persona.application.DeletePersonaUseCase;
import com.ucb.kaffehaus.personal.persona.application.GetAllPersonaUseCase;
import com.ucb.kaffehaus.personal.persona.application.GetOnePersonaUseCase;
import com.ucb.kaffehaus.personal.persona.application.UpdatePersonaUseCase;
import com.ucb.kaffehaus.personal.persona.application.dto.CreatePersonaRequest;
import com.ucb.kaffehaus.personal.persona.application.dto.PersonaResponse;
import com.ucb.kaffehaus.personal.persona.application.dto.UpdatePersonaRequest;
import com.ucb.kaffehaus.personal.persona.domain.Persona;

@RestController
@RequestMapping("/api/v1/persona")
public class PersonaController {

    private final CreatePersonaUseCase createPersonaUseCase;
    private final UpdatePersonaUseCase updatePersonaUseCase;
    private final GetAllPersonaUseCase getAllPersonaUseCase;
    private final GetOnePersonaUseCase getOnePersonaUseCase;
    private final DeletePersonaUseCase deletePersonaUseCase;

    public PersonaController(
            CreatePersonaUseCase createPersonaUseCase,
            UpdatePersonaUseCase updatePersonaUseCase,
            GetAllPersonaUseCase getAllPersonaUseCase,
            GetOnePersonaUseCase getOnePersonaUseCase,
            DeletePersonaUseCase deletePersonaUseCase) {
        this.createPersonaUseCase = createPersonaUseCase;
        this.updatePersonaUseCase = updatePersonaUseCase;
        this.getAllPersonaUseCase = getAllPersonaUseCase;
        this.getOnePersonaUseCase = getOnePersonaUseCase;
        this.deletePersonaUseCase = deletePersonaUseCase;
    }

    @PostMapping
    public ResponseEntity<?> createPersona(@RequestBody CreatePersonaRequest request) {
        request.validate();
        Persona persona = createPersonaUseCase.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(PersonaResponse.from(persona));
    }

    @GetMapping
    public ResponseEntity<List<PersonaResponse>> getAllPersona() {
        List<PersonaResponse> personas = this.getAllPersonaUseCase.execute().stream()
                .map(PersonaResponse::from)
                .toList();
        return ResponseEntity.ok(personas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonaResponse> getOnePersona(@PathVariable UUID id) {
        Persona persona = this.getOnePersonaUseCase.execute(id).get();
        return ResponseEntity.ok(PersonaResponse.from(persona));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonaResponse> updatePersona(@PathVariable UUID id, @RequestBody UpdatePersonaRequest request) {
        Persona persona = this.updatePersonaUseCase.execute(id, request).get();
        return ResponseEntity.ok(PersonaResponse.from(persona));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePersona(@PathVariable UUID id) {
        boolean deleted = this.deletePersonaUseCase.execute(id);
        return ResponseEntity.ok(deleted);
    }
}
