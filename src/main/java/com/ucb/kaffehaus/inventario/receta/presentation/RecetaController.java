package com.ucb.kaffehaus.inventario.receta.presentation;

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

import com.ucb.kaffehaus.inventario.receta.application.CreateRecetaUseCase;
import com.ucb.kaffehaus.inventario.receta.application.DeleteRecetaUseCase;
import com.ucb.kaffehaus.inventario.receta.application.GetAllRecetaUseCase;
import com.ucb.kaffehaus.inventario.receta.application.GetOneRecetaUseCase;
import com.ucb.kaffehaus.inventario.receta.application.UpdateRecetaUseCase;
import com.ucb.kaffehaus.inventario.receta.application.dto.CreateRecetaRequest;
import com.ucb.kaffehaus.inventario.receta.application.dto.RecetaResponse;
import com.ucb.kaffehaus.inventario.receta.application.dto.UpdateRecetaRequest;
import com.ucb.kaffehaus.inventario.receta.domain.Receta;

@RestController
@RequestMapping("/api/v1/receta")
public class RecetaController {

    private final CreateRecetaUseCase createRecetaUseCase;
    private final UpdateRecetaUseCase updateRecetaUseCase;
    private final GetAllRecetaUseCase getAllRecetaUseCase;
    private final GetOneRecetaUseCase getOneRecetaUseCase;
    private final DeleteRecetaUseCase deleteRecetaUseCase;

    public RecetaController(
            CreateRecetaUseCase createRecetaUseCase,
            UpdateRecetaUseCase updateRecetaUseCase,
            GetAllRecetaUseCase getAllRecetaUseCase,
            GetOneRecetaUseCase getOneRecetaUseCase,
            DeleteRecetaUseCase deleteRecetaUseCase) {
        this.createRecetaUseCase = createRecetaUseCase;
        this.updateRecetaUseCase = updateRecetaUseCase;
        this.getAllRecetaUseCase = getAllRecetaUseCase;
        this.getOneRecetaUseCase = getOneRecetaUseCase;
        this.deleteRecetaUseCase = deleteRecetaUseCase;
    }

    @PostMapping
    public ResponseEntity<RecetaResponse> createReceta(@RequestBody CreateRecetaRequest request) {
        request.validate();
        Receta receta = this.createRecetaUseCase.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(RecetaResponse.from(receta));
    }

    @GetMapping
    public ResponseEntity<List<RecetaResponse>> getAllReceta() {
        List<RecetaResponse> recetas = this.getAllRecetaUseCase.execute().stream()
                .map(RecetaResponse::from)
                .toList();
        return ResponseEntity.ok(recetas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecetaResponse> getOneReceta(@PathVariable UUID id) {
        Receta receta = this.getOneRecetaUseCase.execute(id).get();
        return ResponseEntity.ok(RecetaResponse.from(receta));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecetaResponse> updateReceta(@PathVariable UUID id, @RequestBody UpdateRecetaRequest request) {
        Receta receta = this.updateRecetaUseCase.execute(id, request).get();
        return ResponseEntity.ok(RecetaResponse.from(receta));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReceta(@PathVariable UUID id) {
        boolean deleted = this.deleteRecetaUseCase.execute(id);
        return ResponseEntity.ok(deleted);
    }
}
