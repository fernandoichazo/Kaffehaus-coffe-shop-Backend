package com.ucb.kaffehaus.inventario.lote.presentation;

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

import com.ucb.kaffehaus.inventario.lote.application.CreateLoteUseCase;
import com.ucb.kaffehaus.inventario.lote.application.DeleteLoteUseCase;
import com.ucb.kaffehaus.inventario.lote.application.GetAllLoteUseCase;
import com.ucb.kaffehaus.inventario.lote.application.GetOneLoteUseCase;
import com.ucb.kaffehaus.inventario.lote.application.UpdateLoteUseCase;
import com.ucb.kaffehaus.inventario.lote.application.dto.CreateLoteRequest;
import com.ucb.kaffehaus.inventario.lote.application.dto.LoteResponse;
import com.ucb.kaffehaus.inventario.lote.application.dto.UpdateLoteRequest;
import com.ucb.kaffehaus.inventario.lote.domain.Lote;

@RestController
@RequestMapping("/api/v1/lote")
public class LoteController {

    private final CreateLoteUseCase createLoteUseCase;
    private final UpdateLoteUseCase updateLoteUseCase;
    private final GetAllLoteUseCase getAllLoteUseCase;
    private final GetOneLoteUseCase getOneLoteUseCase;
    private final DeleteLoteUseCase deleteLoteUseCase;

    public LoteController(
            CreateLoteUseCase createLoteUseCase,
            UpdateLoteUseCase updateLoteUseCase,
            GetAllLoteUseCase getAllLoteUseCase,
            GetOneLoteUseCase getOneLoteUseCase,
            DeleteLoteUseCase deleteLoteUseCase) {
        this.createLoteUseCase = createLoteUseCase;
        this.updateLoteUseCase = updateLoteUseCase;
        this.getAllLoteUseCase = getAllLoteUseCase;
        this.getOneLoteUseCase = getOneLoteUseCase;
        this.deleteLoteUseCase = deleteLoteUseCase;
    }

    @PostMapping
    public ResponseEntity<LoteResponse> createLote(@RequestBody CreateLoteRequest request) {
        request.validate();
        Lote lote = this.createLoteUseCase.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(LoteResponse.from(lote));
    }

    @GetMapping
    public ResponseEntity<List<LoteResponse>> getAllLote() {
        List<LoteResponse> lotes = this.getAllLoteUseCase.execute().stream()
                .map(LoteResponse::from)
                .toList();
        return ResponseEntity.ok(lotes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoteResponse> getOneLote(@PathVariable UUID id) {
        Lote lote = this.getOneLoteUseCase.execute(id).get();
        return ResponseEntity.ok(LoteResponse.from(lote));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LoteResponse> updateLote(@PathVariable UUID id, @RequestBody UpdateLoteRequest request) {
        Lote lote = this.updateLoteUseCase.execute(id, request).get();
        return ResponseEntity.ok(LoteResponse.from(lote));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLote(@PathVariable UUID id) {
        boolean deleted = this.deleteLoteUseCase.execute(id);
        return ResponseEntity.ok(deleted);
    }
}
