package com.ucb.kaffehaus.personal.proveedor.infrastructure;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ucb.kaffehaus.personal.proveedor.domain.Proveedor;
import com.ucb.kaffehaus.personal.proveedor.domain.ProveedorDatasource;
import com.ucb.kaffehaus.personal.proveedor.domain.ProveedorRepository;

@Service
public class ProveedorRepositoryImpl implements ProveedorRepository {

    private final ProveedorDatasource datasource;

    public ProveedorRepositoryImpl(ProveedorDatasource datasource) {
        this.datasource = datasource;
    }

    @Override
    public Proveedor save(Proveedor proveedor) {
        return this.datasource.save(proveedor);
    }

    @Override
    public Optional<Proveedor> update(UUID Id, Proveedor proveedor) {
        return this.datasource.update(Id, proveedor);
    }

    @Override
    public List<Proveedor> getAll() {
        return this.datasource.getAll();
    }

    @Override
    public Optional<Proveedor> findOne(UUID Id) {
        return this.datasource.findOne(Id);
    }

    @Override
    public boolean deleteOne(UUID Id) {
        return this.datasource.deleteOne(Id);
    }
}
