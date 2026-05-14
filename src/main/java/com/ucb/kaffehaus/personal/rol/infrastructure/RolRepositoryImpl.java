package com.ucb.kaffehaus.personal.rol.infrastructure;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ucb.kaffehaus.personal.rol.domain.Rol;
import com.ucb.kaffehaus.personal.rol.domain.RolDatasource;
import com.ucb.kaffehaus.personal.rol.domain.RolRepository;

@Service
public class RolRepositoryImpl implements RolRepository {

    private final RolDatasource datasource;

    public RolRepositoryImpl(RolDatasource datasource) {
        this.datasource = datasource;
    }

    @Override
    public Rol save(Rol rol) {
        return this.datasource.save(rol);
    }

    @Override
    public Optional<Rol> update(UUID Id, Rol rol) {
        return this.datasource.update(Id, rol);
    }

    @Override
    public List<Rol> getAll() {
        return this.datasource.getAll();
    }

    @Override
    public Optional<Rol> findOne(UUID Id) {
        return this.datasource.findOne(Id);
    }

    @Override
    public boolean deleteOne(UUID Id) {
        return this.datasource.deleteOne(Id);
    }
}
