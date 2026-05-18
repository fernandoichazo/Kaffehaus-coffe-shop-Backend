package com.ucb.kaffehaus.inventario.ingredientesReceta.presentation;

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

import com.ucb.kaffehaus.inventario.ingredientesReceta.application.CreateIngredientesRecetaUseCase;
import com.ucb.kaffehaus.inventario.ingredientesReceta.application.DeleteIngredientesRecetaUseCase;
import com.ucb.kaffehaus.inventario.ingredientesReceta.application.GetAllIngredientesRecetaUseCase;
import com.ucb.kaffehaus.inventario.ingredientesReceta.application.GetOneIngredientesRecetaUseCase;
import com.ucb.kaffehaus.inventario.ingredientesReceta.application.UpdateIngredientesRecetaUseCase;
import com.ucb.kaffehaus.inventario.ingredientesReceta.application.dto.CreateIngredientesRecetaRequest;
import com.ucb.kaffehaus.inventario.ingredientesReceta.application.dto.IngredientesRecetaResponse;
import com.ucb.kaffehaus.inventario.ingredientesReceta.application.dto.UpdateIngredientesRecetaRequest;
import com.ucb.kaffehaus.inventario.ingredientesReceta.domain.IngredientesReceta;

@RestController
@RequestMapping("/api/v1/ingredientes-receta")
public class IngredientesRecetaController {

    private final CreateIngredientesRecetaUseCase createIngredientesRecetaUseCase;
    private final UpdateIngredientesRecetaUseCase updateIngredientesRecetaUseCase;
    private final GetAllIngredientesRecetaUseCase getAllIngredientesRecetaUseCase;
    private final GetOneIngredientesRecetaUseCase getOneIngredientesRecetaUseCase;
    private final DeleteIngredientesRecetaUseCase deleteIngredientesRecetaUseCase;

    public IngredientesRecetaController(
            CreateIngredientesRecetaUseCase createIngredientesRecetaUseCase,
            UpdateIngredientesRecetaUseCase updateIngredientesRecetaUseCase,
            GetAllIngredientesRecetaUseCase getAllIngredientesRecetaUseCase,
            GetOneIngredientesRecetaUseCase getOneIngredientesRecetaUseCase,
            DeleteIngredientesRecetaUseCase deleteIngredientesRecetaUseCase) {
        this.createIngredientesRecetaUseCase = createIngredientesRecetaUseCase;
        this.updateIngredientesRecetaUseCase = updateIngredientesRecetaUseCase;
        this.getAllIngredientesRecetaUseCase = getAllIngredientesRecetaUseCase;
        this.getOneIngredientesRecetaUseCase = getOneIngredientesRecetaUseCase;
        this.deleteIngredientesRecetaUseCase = deleteIngredientesRecetaUseCase;
    }

    @PostMapping
    public ResponseEntity<IngredientesRecetaResponse> createIngredientesReceta(@RequestBody CreateIngredientesRecetaRequest request) {
        request.validate();
        IngredientesReceta ingredientes = this.createIngredientesRecetaUseCase.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(IngredientesRecetaResponse.from(ingredientes));
    }

    @GetMapping
    public ResponseEntity<List<IngredientesRecetaResponse>> getAllIngredientesReceta() {
        List<IngredientesRecetaResponse> ingredientes = this.getAllIngredientesRecetaUseCase.execute().stream()
                .map(IngredientesRecetaResponse::from)
                .toList();
        return ResponseEntity.ok(ingredientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IngredientesRecetaResponse> getOneIngredientesReceta(@PathVariable UUID id) {
        IngredientesReceta ingredientes = this.getOneIngredientesRecetaUseCase.execute(id).get();
        return ResponseEntity.ok(IngredientesRecetaResponse.from(ingredientes));
    }

    @PutMapping("/{id}")
    public ResponseEntity<IngredientesRecetaResponse> updateIngredientesReceta(
            @PathVariable UUID id,
            @RequestBody UpdateIngredientesRecetaRequest request) {
        IngredientesReceta ingredientes = this.updateIngredientesRecetaUseCase.execute(id, request).get();
        return ResponseEntity.ok(IngredientesRecetaResponse.from(ingredientes));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteIngredientesReceta(@PathVariable UUID id) {
        boolean deleted = this.deleteIngredientesRecetaUseCase.execute(id);
        return ResponseEntity.ok(deleted);
    }
}
