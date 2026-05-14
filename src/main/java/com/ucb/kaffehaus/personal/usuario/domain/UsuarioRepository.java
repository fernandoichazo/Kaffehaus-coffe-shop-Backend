package com.ucb.kaffehaus.personal.usuario.domain;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository {
    Usuario save(Usuario usuario);
    Optional<Usuario> update(int Id, Usuario usuario);
    List<Usuario> getAll();
    Optional<Usuario> findOne(int Id);
    boolean deleteOne(int Id);
}
