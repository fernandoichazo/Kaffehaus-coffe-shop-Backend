package com.ucb.kaffehaus.inventario.detalleLote.presentation;

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

import com.ucb.kaffehaus.inventario.detalleLote.application.CreateDetalleLoteUseCase;
import com.ucb.kaffehaus.inventario.detalleLote.application.DeleteDetalleLoteUseCase;
import com.ucb.kaffehaus.inventario.detalleLote.application.GetAllDetalleLoteUseCase;
import com.ucb.kaffehaus.inventario.detalleLote.application.GetOneDetalleLoteUseCase;
import com.ucb.kaffehaus.inventario.detalleLote.application.UpdateDetalleLoteUseCase;
import com.ucb.kaffehaus.inventario.detalleLote.application.dto.CreateDetalleLoteRequest;
import com.ucb.kaffehaus.inventario.detalleLote.application.dto.DetalleLoteResponse;
import com.ucb.kaffehaus.inventario.detalleLote.application.dto.UpdateDetalleLoteRequest;
import com.ucb.kaffehaus.inventario.detalleLote.domain.DetalleLote;

@RestController
@RequestMapping("/api/v1/detalle-lote")
public class DetalleLoteController {

    private final CreateDetalleLoteUseCase createDetalleLoteUseCase;
    private final UpdateDetalleLoteUseCase updateDetalleLoteUseCase;
    private final GetAllDetalleLoteUseCase getAllDetalleLoteUseCase;
    private final GetOneDetalleLoteUseCase getOneDetalleLoteUseCase;
    private final DeleteDetalleLoteUseCase deleteDetalleLoteUseCase;

    public DetalleLoteController(
            CreateDetalleLoteUseCase createDetalleLoteUseCase,
            UpdateDetalleLoteUseCase updateDetalleLoteUseCase,
            GetAllDetalleLoteUseCase getAllDetalleLoteUseCase,
            GetOneDetalleLoteUseCase getOneDetalleLoteUseCase,
            DeleteDetalleLoteUseCase deleteDetalleLoteUseCase) {
        this.createDetalleLoteUseCase = createDetalleLoteUseCase;
        this.updateDetalleLoteUseCase = updateDetalleLoteUseCase;
        this.getAllDetalleLoteUseCase = getAllDetalleLoteUseCase;
        this.getOneDetalleLoteUseCase = getOneDetalleLoteUseCase;
        this.deleteDetalleLoteUseCase = deleteDetalleLoteUseCase;
    }

    @PostMapping
    public ResponseEntity<DetalleLoteResponse> createDetalleLote(@RequestBody CreateDetalleLoteRequest request) {
        request.validate();
        DetalleLote detalleLote = this.createDetalleLoteUseCase.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(DetalleLoteResponse.from(detalleLote));
    }

    @GetMapping
    public ResponseEntity<List<DetalleLoteResponse>> getAllDetalleLote() {
        List<DetalleLoteResponse> detalles = this.getAllDetalleLoteUseCase.execute().stream()
                .map(DetalleLoteResponse::from)
                .toList();
        return ResponseEntity.ok(detalles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleLoteResponse> getOneDetalleLote(@PathVariable UUID id) {
        DetalleLote detalleLote = this.getOneDetalleLoteUseCase.execute(id).get();
        return ResponseEntity.ok(DetalleLoteResponse.from(detalleLote));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalleLoteResponse> updateDetalleLote(@PathVariable UUID id, @RequestBody UpdateDetalleLoteRequest request) {
        DetalleLote detalleLote = this.updateDetalleLoteUseCase.execute(id, request).get();
        return ResponseEntity.ok(DetalleLoteResponse.from(detalleLote));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDetalleLote(@PathVariable UUID id) {
        boolean deleted = this.deleteDetalleLoteUseCase.execute(id);
        return ResponseEntity.ok(deleted);
    }
}
