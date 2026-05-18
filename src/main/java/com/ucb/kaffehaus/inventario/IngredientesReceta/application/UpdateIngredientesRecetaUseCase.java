package com.ucb.kaffehaus.inventario.ingredientesReceta.application;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ucb.kaffehaus.inventario.ingredientesReceta.application.dto.UpdateIngredientesRecetaRequest;
import com.ucb.kaffehaus.inventario.ingredientesReceta.domain.IngredientesReceta;
import com.ucb.kaffehaus.inventario.ingredientesReceta.domain.IngredientesRecetaRepository;

@Service
@Transactional
public class UpdateIngredientesRecetaUseCase {

    private final IngredientesRecetaRepository ingredientesRecetaRepository;

    public UpdateIngredientesRecetaUseCase(IngredientesRecetaRepository ingredientesRecetaRepository) {
        this.ingredientesRecetaRepository = ingredientesRecetaRepository;
    }

    public Optional<IngredientesReceta> execute(UUID id, UpdateIngredientesRecetaRequest request) {
        request.validate();

        IngredientesReceta ingredientesToUpdate = IngredientesReceta.restore(
                id,
                request.getRecetaId(),
                request.getProductoId(),
                request.getCantidad(),
                false);

        return this.ingredientesRecetaRepository.update(id, ingredientesToUpdate);
    }
}
