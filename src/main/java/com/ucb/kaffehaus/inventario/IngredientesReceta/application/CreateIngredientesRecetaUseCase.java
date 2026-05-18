package com.ucb.kaffehaus.inventario.ingredientesReceta.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ucb.kaffehaus.inventario.ingredientesReceta.application.dto.CreateIngredientesRecetaRequest;
import com.ucb.kaffehaus.inventario.ingredientesReceta.domain.IngredientesReceta;
import com.ucb.kaffehaus.inventario.ingredientesReceta.domain.IngredientesRecetaRepository;

@Service
@Transactional
public class CreateIngredientesRecetaUseCase {

    private final IngredientesRecetaRepository ingredientesRecetaRepository;

    public CreateIngredientesRecetaUseCase(IngredientesRecetaRepository ingredientesRecetaRepository) {
        this.ingredientesRecetaRepository = ingredientesRecetaRepository;
    }

    public IngredientesReceta execute(CreateIngredientesRecetaRequest request) {
        request.validate();
        IngredientesReceta ingredientes = IngredientesReceta.create(
                request.getRecetaId(),
                request.getProductoId(),
                request.getCantidad());
        return this.ingredientesRecetaRepository.save(ingredientes);
    }
}
