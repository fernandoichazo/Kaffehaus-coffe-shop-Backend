package com.ucb.kaffehaus.personal.usuario.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ucb.kaffehaus.personal.usuario.domain.UsuarioRepository;

@Service
@Transactional
public class DeleteUsuarioUseCase {

    private final UsuarioRepository usuarioRepository;

    public DeleteUsuarioUseCase(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public boolean execute(int id) {
        return this.usuarioRepository.deleteOne(id);
    }
}
