package com.ucb.kaffehaus.inventario.categoria.presentation;

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

import com.ucb.kaffehaus.inventario.categoria.application.CreateCategoriaUseCase;
import com.ucb.kaffehaus.inventario.categoria.application.DeleteCategoriaUseCase;
import com.ucb.kaffehaus.inventario.categoria.application.GetAllCategoriaUseCase;
import com.ucb.kaffehaus.inventario.categoria.application.GetOneCategoriaUseCase;
import com.ucb.kaffehaus.inventario.categoria.application.UpdateCategoriaUseCase;
import com.ucb.kaffehaus.inventario.categoria.application.dto.CategoriaResponse;
import com.ucb.kaffehaus.inventario.categoria.application.dto.CreateCategoriaRequest;
import com.ucb.kaffehaus.inventario.categoria.application.dto.UpdateCategoriaRequest;
import com.ucb.kaffehaus.inventario.categoria.domain.Categoria;

@RestController
@RequestMapping("/api/v1/categoria")
public class CategoriaController {

    private final CreateCategoriaUseCase createCategoriaUseCase;
    private final UpdateCategoriaUseCase updateCategoriaUseCase;
    private final GetAllCategoriaUseCase getAllCategoriaUseCase;
    private final GetOneCategoriaUseCase getOneCategoriaUseCase;
    private final DeleteCategoriaUseCase deleteCategoriaUseCase;

    public CategoriaController(
            CreateCategoriaUseCase createCategoriaUseCase,
            UpdateCategoriaUseCase updateCategoriaUseCase,
            GetAllCategoriaUseCase getAllCategoriaUseCase,
            GetOneCategoriaUseCase getOneCategoriaUseCase,
            DeleteCategoriaUseCase deleteCategoriaUseCase) {
        this.createCategoriaUseCase = createCategoriaUseCase;
        this.updateCategoriaUseCase = updateCategoriaUseCase;
        this.getAllCategoriaUseCase = getAllCategoriaUseCase;
        this.getOneCategoriaUseCase = getOneCategoriaUseCase;
        this.deleteCategoriaUseCase = deleteCategoriaUseCase;
    }

    @PostMapping
    public ResponseEntity<CategoriaResponse> createCategoria(@RequestBody CreateCategoriaRequest request) {
        request.validate();
        Categoria categoria = this.createCategoriaUseCase.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(CategoriaResponse.from(categoria));
    }

    @GetMapping
    public ResponseEntity<List<CategoriaResponse>> getAllCategoria() {
        List<CategoriaResponse> categorias = this.getAllCategoriaUseCase.execute().stream()
                .map(CategoriaResponse::from)
                .toList();
        return ResponseEntity.ok(categorias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponse> getOneCategoria(@PathVariable UUID id) {
        Categoria categoria = this.getOneCategoriaUseCase.execute(id).get();
        return ResponseEntity.ok(CategoriaResponse.from(categoria));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponse> updateCategoria(@PathVariable UUID id, @RequestBody UpdateCategoriaRequest request) {
        Categoria categoria = this.updateCategoriaUseCase.execute(id, request).get();
        return ResponseEntity.ok(CategoriaResponse.from(categoria));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategoria(@PathVariable UUID id) {
        boolean deleted = this.deleteCategoriaUseCase.execute(id);
        return ResponseEntity.ok(deleted);
    }
}
