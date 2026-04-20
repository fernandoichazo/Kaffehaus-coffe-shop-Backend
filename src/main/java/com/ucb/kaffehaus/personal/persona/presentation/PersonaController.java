package com.ucb.kaffehaus.personal.persona.presentation;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.ucb.kaffehaus.shared.application.exception.ErrorResponse;

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
    public ResponseEntity<?> getOnePersona(@PathVariable int id) {
        return this.getOnePersonaUseCase.execute(id)
                .<ResponseEntity<?>>map(persona -> ResponseEntity.ok(PersonaResponse.from(persona)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(this.buildNotFoundError(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePersona(@PathVariable int id, @RequestBody UpdatePersonaRequest request) {
        return this.updatePersonaUseCase.execute(id, request)
            .<ResponseEntity<?>>map(persona -> ResponseEntity.ok(PersonaResponse.from(persona)))
            .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(this.buildNotFoundError(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePersona(@PathVariable int id) {
        boolean deleted = this.deletePersonaUseCase.execute(id);
        if (!deleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(this.buildNotFoundError(id));
        }
        return ResponseEntity.noContent().build();
    }

    private ErrorResponse buildNotFoundError(int id) {
        Map<String, String> errors = new HashMap<>();
        errors.put("persona", "No se encontró persona con id " + id);

        return new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Recurso no encontrado",
                errors);
    }
}
