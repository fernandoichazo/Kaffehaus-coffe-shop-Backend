package com.ucb.kaffehaus.personal.rol.presentation;

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

import com.ucb.kaffehaus.personal.rol.application.CreateRolUseCase;
import com.ucb.kaffehaus.personal.rol.application.DeleteRolUseCase;
import com.ucb.kaffehaus.personal.rol.application.GetAllRolUseCase;
import com.ucb.kaffehaus.personal.rol.application.GetOneRolUseCase;
import com.ucb.kaffehaus.personal.rol.application.UpdateRolUseCase;
import com.ucb.kaffehaus.personal.rol.application.dto.CreateRolRequest;
import com.ucb.kaffehaus.personal.rol.application.dto.RolResponse;
import com.ucb.kaffehaus.personal.rol.application.dto.UpdateRolRequest;
import com.ucb.kaffehaus.personal.rol.domain.Rol;

@RestController
@RequestMapping("/api/v1/rol")
public class RolController {

    private final CreateRolUseCase createRolUseCase;
    private final UpdateRolUseCase updateRolUseCase;
    private final GetAllRolUseCase getAllRolUseCase;
    private final GetOneRolUseCase getOneRolUseCase;
    private final DeleteRolUseCase deleteRolUseCase;

    public RolController(
            CreateRolUseCase createRolUseCase,
            UpdateRolUseCase updateRolUseCase,
            GetAllRolUseCase getAllRolUseCase,
            GetOneRolUseCase getOneRolUseCase,
            DeleteRolUseCase deleteRolUseCase) {
        this.createRolUseCase = createRolUseCase;
        this.updateRolUseCase = updateRolUseCase;
        this.getAllRolUseCase = getAllRolUseCase;
        this.getOneRolUseCase = getOneRolUseCase;
        this.deleteRolUseCase = deleteRolUseCase;
    }

    @PostMapping
    public ResponseEntity<RolResponse> createRol(@RequestBody CreateRolRequest request) {
        request.validate();
        Rol rol = this.createRolUseCase.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(RolResponse.from(rol));
    }

    @GetMapping
    public ResponseEntity<List<RolResponse>> getAllRol() {
        List<RolResponse> roles = this.getAllRolUseCase.execute().stream()
                .map(RolResponse::from)
                .toList();
        return ResponseEntity.ok(roles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RolResponse> getOneRol(@PathVariable UUID id) {
        Rol rol = this.getOneRolUseCase.execute(id).get();
        return ResponseEntity.ok(RolResponse.from(rol));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RolResponse> updateRol(@PathVariable UUID id, @RequestBody UpdateRolRequest request) {
        Rol rol = this.updateRolUseCase.execute(id, request).get();
        return ResponseEntity.ok(RolResponse.from(rol));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRol(@PathVariable UUID id) {
        boolean deleted = this.deleteRolUseCase.execute(id);
        return ResponseEntity.ok(deleted);
    }
}
