package com.ucb.kaffehaus.inventario.ingredientesReceta.application;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ucb.kaffehaus.inventario.ingredientesReceta.domain.IngredientesReceta;
import com.ucb.kaffehaus.inventario.ingredientesReceta.domain.IngredientesRecetaRepository;

@Service
@Transactional(readOnly = true)
public class GetOneIngredientesRecetaUseCase {

    private final IngredientesRecetaRepository ingredientesRecetaRepository;

    public GetOneIngredientesRecetaUseCase(IngredientesRecetaRepository ingredientesRecetaRepository) {
        this.ingredientesRecetaRepository = ingredientesRecetaRepository;
    }

    public Optional<IngredientesReceta> execute(UUID id) {
        return this.ingredientesRecetaRepository.findOne(id)
                .filter(ingrediente -> !ingrediente.isBorrado());
    }
}
