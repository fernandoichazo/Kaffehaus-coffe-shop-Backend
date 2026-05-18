package com.ucb.kaffehaus.inventario.categoria.infrastructure;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ucb.kaffehaus.inventario.categoria.domain.Categoria;
import com.ucb.kaffehaus.inventario.categoria.domain.CategoriaDatasource;
import com.ucb.kaffehaus.inventario.categoria.domain.CategoriaRepository;

@Service
public class CategoriaRepositoryImpl implements CategoriaRepository {

    private final CategoriaDatasource datasource;

    public CategoriaRepositoryImpl(CategoriaDatasource datasource) {
        this.datasource = datasource;
    }

    @Override
    public Categoria save(Categoria categoria) {
        return this.datasource.save(categoria);
    }

    @Override
    public Optional<Categoria> update(UUID id, Categoria categoria) {
        return this.datasource.update(id, categoria);
    }

    @Override
    public List<Categoria> getAll() {
        return this.datasource.getAll();
    }

    @Override
    public Optional<Categoria> findOne(UUID id) {
        return this.datasource.findOne(id);
    }

    @Override
    public boolean deleteOne(UUID id) {
        return this.datasource.deleteOne(id);
    }
}
