package com.ucb.kaffehaus.inventario.receta.infrastructure;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ucb.kaffehaus.inventario.receta.domain.Receta;
import com.ucb.kaffehaus.inventario.receta.domain.RecetaDatasource;
import com.ucb.kaffehaus.inventario.receta.domain.RecetaRepository;

@Service
public class RecetaRepositoryImpl implements RecetaRepository {

    private final RecetaDatasource datasource;

    public RecetaRepositoryImpl(RecetaDatasource datasource) {
        this.datasource = datasource;
    }

    @Override
    public Receta save(Receta receta) {
        return this.datasource.save(receta);
    }

    @Override
    public Optional<Receta> update(UUID id, Receta receta) {
        return this.datasource.update(id, receta);
    }

    @Override
    public List<Receta> getAll() {
        return this.datasource.getAll();
    }

    @Override
    public Optional<Receta> findOne(UUID id) {
        return this.datasource.findOne(id);
    }

    @Override
    public boolean deleteOne(UUID id) {
        return this.datasource.deleteOne(id);
    }
}
