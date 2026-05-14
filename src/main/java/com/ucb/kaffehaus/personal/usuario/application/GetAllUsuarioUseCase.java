package com.ucb.kaffehaus.personal.usuario.application;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ucb.kaffehaus.personal.usuario.domain.Usuario;
import com.ucb.kaffehaus.personal.usuario.domain.UsuarioRepository;

@Service
@Transactional(readOnly = true)
public class GetAllUsuarioUseCase {

    private final UsuarioRepository usuarioRepository;

    public GetAllUsuarioUseCase(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> execute() {
        return this.usuarioRepository.getAll();
    }
}
