package com.ucb.kaffehaus.personal.entidad.presentation;

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

import com.ucb.kaffehaus.personal.entidad.application.CreateEntidadUseCase;
import com.ucb.kaffehaus.personal.entidad.application.DeleteEntidadUseCase;
import com.ucb.kaffehaus.personal.entidad.application.GetAllEntidadUseCase;
import com.ucb.kaffehaus.personal.entidad.application.GetOneEntidadUseCase;
import com.ucb.kaffehaus.personal.entidad.application.UpdateEntidadUseCase;
import com.ucb.kaffehaus.personal.entidad.application.dto.CreateEntidadRequest;
import com.ucb.kaffehaus.personal.entidad.application.dto.EntidadResponse;
import com.ucb.kaffehaus.personal.entidad.application.dto.UpdateEntidadRequest;
import com.ucb.kaffehaus.personal.entidad.domain.Entidad;

@RestController
@RequestMapping("/api/v1/entidad")
public class EntidadController {

    private final CreateEntidadUseCase createEntidadUseCase;
    private final UpdateEntidadUseCase updateEntidadUseCase;
    private final GetAllEntidadUseCase getAllEntidadUseCase;
    private final GetOneEntidadUseCase getOneEntidadUseCase;
    private final DeleteEntidadUseCase deleteEntidadUseCase;

    public EntidadController(
            CreateEntidadUseCase createEntidadUseCase,
            UpdateEntidadUseCase updateEntidadUseCase,
            GetAllEntidadUseCase getAllEntidadUseCase,
            GetOneEntidadUseCase getOneEntidadUseCase,
            DeleteEntidadUseCase deleteEntidadUseCase) {
        this.createEntidadUseCase = createEntidadUseCase;
        this.updateEntidadUseCase = updateEntidadUseCase;
        this.getAllEntidadUseCase = getAllEntidadUseCase;
        this.getOneEntidadUseCase = getOneEntidadUseCase;
        this.deleteEntidadUseCase = deleteEntidadUseCase;
    }

    @PostMapping
    public ResponseEntity<EntidadResponse> createEntidad(@RequestBody CreateEntidadRequest request) {
        request.validate();
        Entidad entidad = this.createEntidadUseCase.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(EntidadResponse.from(entidad));
    }

    @GetMapping
    public ResponseEntity<List<EntidadResponse>> getAllEntidad() {
        List<EntidadResponse> entidades = this.getAllEntidadUseCase.execute().stream()
                .map(EntidadResponse::from)
                .toList();
        return ResponseEntity.ok(entidades);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntidadResponse> getOneEntidad(@PathVariable UUID id) {
        Entidad entidad = this.getOneEntidadUseCase.execute(id).get();
        return ResponseEntity.ok(EntidadResponse.from(entidad));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntidadResponse> updateEntidad(@PathVariable UUID id, @RequestBody UpdateEntidadRequest request) {
        Entidad entidad = this.updateEntidadUseCase.execute(id, request).get();
        return ResponseEntity.ok(EntidadResponse.from(entidad));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEntidad(@PathVariable UUID id) {
        boolean deleted = this.deleteEntidadUseCase.execute(id);
        return ResponseEntity.ok(deleted);
    }
}
