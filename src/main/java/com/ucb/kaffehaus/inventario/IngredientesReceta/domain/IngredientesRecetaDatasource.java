package com.ucb.kaffehaus.inventario.ingredientesReceta.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IngredientesRecetaDatasource {
    IngredientesReceta save(IngredientesReceta ingredientesReceta);
    Optional<IngredientesReceta> update(UUID id, IngredientesReceta ingredientesReceta);
    List<IngredientesReceta> getAll();
    Optional<IngredientesReceta> findOne(UUID id);
    boolean deleteOne(UUID id);
}
