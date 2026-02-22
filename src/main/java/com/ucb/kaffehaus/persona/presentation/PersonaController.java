package com.ucb.kaffehaus.persona.presentation;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ucb.kaffehaus.persona.application.CreatePersonaUseCase;
import com.ucb.kaffehaus.persona.application.dto.CreatePersonaRequest;
import com.ucb.kaffehaus.persona.application.dto.PersonaResponse;
import com.ucb.kaffehaus.persona.domain.Persona;
import com.ucb.kaffehaus.shared.application.exception.ValidationException;
import com.ucb.kaffehaus.shared.application.exception.ErrorResponse;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/persona")
public class PersonaController {

    private final CreatePersonaUseCase createPersonaUseCase;

    public PersonaController(CreatePersonaUseCase createPersonaUseCase) {
        this.createPersonaUseCase = createPersonaUseCase;
    }

    @PostMapping
    public ResponseEntity<?> createPersona(@RequestBody CreatePersonaRequest request) {
        try {
            Persona persona = createPersonaUseCase.execute(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(PersonaResponse.from(persona));
        } catch (ValidationException e) {
            ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Error de validación",
                e.getErrors()
            );
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }
    
}
