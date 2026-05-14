package com.ucb.kaffehaus.personal.usuario.presentation;

import java.util.List;

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

import com.ucb.kaffehaus.personal.usuario.application.CreateUsuarioUseCase;
import com.ucb.kaffehaus.personal.usuario.application.DeleteUsuarioUseCase;
import com.ucb.kaffehaus.personal.usuario.application.GetAllUsuarioUseCase;
import com.ucb.kaffehaus.personal.usuario.application.GetOneUsuarioUseCase;
import com.ucb.kaffehaus.personal.usuario.application.UpdateUsuarioUseCase;
import com.ucb.kaffehaus.personal.usuario.application.dto.CreateUsuarioRequest;
import com.ucb.kaffehaus.personal.usuario.application.dto.UpdateUsuarioRequest;
import com.ucb.kaffehaus.personal.usuario.application.dto.UsuarioResponse;
import com.ucb.kaffehaus.personal.usuario.domain.Usuario;

@RestController
@RequestMapping("/api/v1/usuario")
public class UsuarioController {

    private final CreateUsuarioUseCase createUsuarioUseCase;
    private final UpdateUsuarioUseCase updateUsuarioUseCase;
    private final GetAllUsuarioUseCase getAllUsuarioUseCase;
    private final GetOneUsuarioUseCase getOneUsuarioUseCase;
    private final DeleteUsuarioUseCase deleteUsuarioUseCase;

    public UsuarioController(
            CreateUsuarioUseCase createUsuarioUseCase,
            UpdateUsuarioUseCase updateUsuarioUseCase,
            GetAllUsuarioUseCase getAllUsuarioUseCase,
            GetOneUsuarioUseCase getOneUsuarioUseCase,
            DeleteUsuarioUseCase deleteUsuarioUseCase) {
        this.createUsuarioUseCase = createUsuarioUseCase;
        this.updateUsuarioUseCase = updateUsuarioUseCase;
        this.getAllUsuarioUseCase = getAllUsuarioUseCase;
        this.getOneUsuarioUseCase = getOneUsuarioUseCase;
        this.deleteUsuarioUseCase = deleteUsuarioUseCase;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponse> createUsuario(@RequestBody CreateUsuarioRequest request) {
        request.validate();
        Usuario usuario = this.createUsuarioUseCase.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioResponse.from(usuario));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> getAllUsuario() {
        List<UsuarioResponse> usuarios = this.getAllUsuarioUseCase.execute().stream()
                .map(UsuarioResponse::from)
                .toList();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> getOneUsuario(@PathVariable int id) {
        Usuario usuario = this.getOneUsuarioUseCase.execute(id).get();
        return ResponseEntity.ok(UsuarioResponse.from(usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> updateUsuario(@PathVariable int id, @RequestBody UpdateUsuarioRequest request) {
        Usuario usuario = this.updateUsuarioUseCase.execute(id, request).get();
        return ResponseEntity.ok(UsuarioResponse.from(usuario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable int id) {
        boolean deleted = this.deleteUsuarioUseCase.execute(id);
        return ResponseEntity.ok(deleted);
    }
}
