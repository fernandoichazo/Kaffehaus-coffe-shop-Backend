package com.ucb.kaffehaus.inventario.ingredientesReceta.infrastructure;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ucb.kaffehaus.inventario.ingredientesReceta.domain.IngredientesReceta;
import com.ucb.kaffehaus.inventario.ingredientesReceta.domain.IngredientesRecetaDatasource;
import com.ucb.kaffehaus.inventario.ingredientesReceta.domain.IngredientesRecetaRepository;

@Service
public class IngredientesRecetaRepositoryImpl implements IngredientesRecetaRepository {

    private final IngredientesRecetaDatasource datasource;

    public IngredientesRecetaRepositoryImpl(IngredientesRecetaDatasource datasource) {
        this.datasource = datasource;
    }

    @Override
    public IngredientesReceta save(IngredientesReceta ingredientesReceta) {
        return this.datasource.save(ingredientesReceta);
    }

    @Override
    public Optional<IngredientesReceta> update(UUID id, IngredientesReceta ingredientesReceta) {
        return this.datasource.update(id, ingredientesReceta);
    }

    @Override
    public List<IngredientesReceta> getAll() {
        return this.datasource.getAll();
    }

    @Override
    public Optional<IngredientesReceta> findOne(UUID id) {
        return this.datasource.findOne(id);
    }

    @Override
    public boolean deleteOne(UUID id) {
        return this.datasource.deleteOne(id);
    }
}
