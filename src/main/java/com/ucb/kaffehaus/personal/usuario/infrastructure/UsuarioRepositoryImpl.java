package com.ucb.kaffehaus.personal.usuario.infrastructure;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ucb.kaffehaus.personal.usuario.domain.Usuario;
import com.ucb.kaffehaus.personal.usuario.domain.UsuarioDatasource;
import com.ucb.kaffehaus.personal.usuario.domain.UsuarioRepository;

@Service
public class UsuarioRepositoryImpl implements UsuarioRepository {

    private final UsuarioDatasource datasource;

    public UsuarioRepositoryImpl(UsuarioDatasource datasource) {
        this.datasource = datasource;
    }

    @Override
    public Usuario save(Usuario usuario) {
        return this.datasource.save(usuario);
    }

    @Override
    public Optional<Usuario> update(int Id, Usuario usuario) {
        return this.datasource.update(Id, usuario);
    }

    @Override
    public List<Usuario> getAll() {
        return this.datasource.getAll();
    }

    @Override
    public Optional<Usuario> findOne(int Id) {
        return this.datasource.findOne(Id);
    }

    @Override
    public boolean deleteOne(int Id) {
        return this.datasource.deleteOne(Id);
    }
}
