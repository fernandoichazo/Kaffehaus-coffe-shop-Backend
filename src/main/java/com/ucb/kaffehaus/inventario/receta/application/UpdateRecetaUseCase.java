package com.ucb.kaffehaus.inventario.receta.application;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ucb.kaffehaus.inventario.receta.application.dto.UpdateRecetaRequest;
import com.ucb.kaffehaus.inventario.receta.domain.Receta;
import com.ucb.kaffehaus.inventario.receta.domain.RecetaRepository;

@Service
@Transactional
public class UpdateRecetaUseCase {

    private final RecetaRepository recetaRepository;

    public UpdateRecetaUseCase(RecetaRepository recetaRepository) {
        this.recetaRepository = recetaRepository;
    }

    public Optional<Receta> execute(UUID id, UpdateRecetaRequest request) {
        request.validate();

        Receta recetaToUpdate = Receta.restore(
                id,
                request.getProductoId(),
                request.getCantidad(),
                false);

        return this.recetaRepository.update(id, recetaToUpdate);
    }
}
