package com.ucb.kaffehaus.inventario.ingredientesReceta.application;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ucb.kaffehaus.inventario.ingredientesReceta.domain.IngredientesRecetaRepository;

@Service
@Transactional
public class DeleteIngredientesRecetaUseCase {

    private final IngredientesRecetaRepository ingredientesRecetaRepository;

    public DeleteIngredientesRecetaUseCase(IngredientesRecetaRepository ingredientesRecetaRepository) {
        this.ingredientesRecetaRepository = ingredientesRecetaRepository;
    }

    public boolean execute(UUID id) {
        return this.ingredientesRecetaRepository.deleteOne(id);
    }
}
