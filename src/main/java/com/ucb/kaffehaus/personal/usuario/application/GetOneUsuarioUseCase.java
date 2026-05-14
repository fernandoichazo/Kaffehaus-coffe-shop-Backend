package com.ucb.kaffehaus.personal.usuario.application;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ucb.kaffehaus.personal.usuario.domain.Usuario;
import com.ucb.kaffehaus.personal.usuario.domain.UsuarioRepository;

@Service
@Transactional(readOnly = true)
public class GetOneUsuarioUseCase {

    private final UsuarioRepository usuarioRepository;

    public GetOneUsuarioUseCase(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Optional<Usuario> execute(int id) {
        return this.usuarioRepository.findOne(id);
    }
}
