package com.ucb.kaffehaus.personal.usuario.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UsuarioDatasource {
    Usuario save(Usuario usuario);
    Optional<Usuario> update(UUID Id, Usuario usuario);
    List<Usuario> getAll();
    Optional<Usuario> findOne(UUID Id);
    boolean deleteOne(UUID Id);
}
