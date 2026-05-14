package com.ucb.kaffehaus.personal.proveedor.presentation;

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

import com.ucb.kaffehaus.personal.proveedor.application.CreateProveedorUseCase;
import com.ucb.kaffehaus.personal.proveedor.application.DeleteProveedorUseCase;
import com.ucb.kaffehaus.personal.proveedor.application.GetAllProveedorUseCase;
import com.ucb.kaffehaus.personal.proveedor.application.GetOneProveedorUseCase;
import com.ucb.kaffehaus.personal.proveedor.application.UpdateProveedorUseCase;
import com.ucb.kaffehaus.personal.proveedor.application.dto.CreateProveedorRequest;
import com.ucb.kaffehaus.personal.proveedor.application.dto.ProveedorResponse;
import com.ucb.kaffehaus.personal.proveedor.application.dto.UpdateProveedorRequest;
import com.ucb.kaffehaus.personal.proveedor.domain.Proveedor;

@RestController
@RequestMapping("/api/v1/proveedor")
public class ProveedorController {

    private final CreateProveedorUseCase createProveedorUseCase;
    private final UpdateProveedorUseCase updateProveedorUseCase;
    private final GetAllProveedorUseCase getAllProveedorUseCase;
    private final GetOneProveedorUseCase getOneProveedorUseCase;
    private final DeleteProveedorUseCase deleteProveedorUseCase;

    public ProveedorController(
            CreateProveedorUseCase createProveedorUseCase,
            UpdateProveedorUseCase updateProveedorUseCase,
            GetAllProveedorUseCase getAllProveedorUseCase,
            GetOneProveedorUseCase getOneProveedorUseCase,
            DeleteProveedorUseCase deleteProveedorUseCase) {
        this.createProveedorUseCase = createProveedorUseCase;
        this.updateProveedorUseCase = updateProveedorUseCase;
        this.getAllProveedorUseCase = getAllProveedorUseCase;
        this.getOneProveedorUseCase = getOneProveedorUseCase;
        this.deleteProveedorUseCase = deleteProveedorUseCase;
    }

    @PostMapping
    public ResponseEntity<ProveedorResponse> createProveedor(@RequestBody CreateProveedorRequest request) {
        request.validate();
        Proveedor proveedor = this.createProveedorUseCase.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ProveedorResponse.from(proveedor));
    }

    @GetMapping
    public ResponseEntity<List<ProveedorResponse>> getAllProveedor() {
        List<ProveedorResponse> proveedores = this.getAllProveedorUseCase.execute().stream()
                .map(ProveedorResponse::from)
                .toList();
        return ResponseEntity.ok(proveedores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProveedorResponse> getOneProveedor(@PathVariable UUID id) {
        Proveedor proveedor = this.getOneProveedorUseCase.execute(id).get();
        return ResponseEntity.ok(ProveedorResponse.from(proveedor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProveedorResponse> updateProveedor(@PathVariable UUID id, @RequestBody UpdateProveedorRequest request) {
        Proveedor proveedor = this.updateProveedorUseCase.execute(id, request).get();
        return ResponseEntity.ok(ProveedorResponse.from(proveedor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProveedor(@PathVariable UUID id) {
        boolean deleted = this.deleteProveedorUseCase.execute(id);
        return ResponseEntity.ok(deleted);
    }
}
