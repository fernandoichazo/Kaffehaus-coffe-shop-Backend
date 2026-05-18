package com.ucb.kaffehaus.inventario.ingredientesReceta.infrastructure;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientesRecetaJpaRepository extends JpaRepository<IngredientesRecetaEntity, UUID> {

}
