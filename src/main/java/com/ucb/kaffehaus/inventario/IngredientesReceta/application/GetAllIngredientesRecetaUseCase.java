package com.ucb.kaffehaus.inventario.ingredientesReceta.application;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ucb.kaffehaus.inventario.ingredientesReceta.domain.IngredientesReceta;
import com.ucb.kaffehaus.inventario.ingredientesReceta.domain.IngredientesRecetaRepository;

@Service
@Transactional(readOnly = true)
public class GetAllIngredientesRecetaUseCase {

    private final IngredientesRecetaRepository ingredientesRecetaRepository;

    public GetAllIngredientesRecetaUseCase(IngredientesRecetaRepository ingredientesRecetaRepository) {
        this.ingredientesRecetaRepository = ingredientesRecetaRepository;
    }

    public List<IngredientesReceta> execute() {
        return this.ingredientesRecetaRepository.getAll();
    }
}
