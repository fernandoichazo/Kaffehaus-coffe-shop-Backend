package com.ucb.kaffehaus.inventario.receta.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ucb.kaffehaus.inventario.receta.application.dto.CreateRecetaRequest;
import com.ucb.kaffehaus.inventario.receta.domain.Receta;
import com.ucb.kaffehaus.inventario.receta.domain.RecetaRepository;

@Service
@Transactional
public class CreateRecetaUseCase {

    private final RecetaRepository recetaRepository;

    public CreateRecetaUseCase(RecetaRepository recetaRepository) {
        this.recetaRepository = recetaRepository;
    }

    public Receta execute(CreateRecetaRequest request) {
        request.validate();
        Receta receta = Receta.create(request.getProductoId(), request.getCantidad());
        return this.recetaRepository.save(receta);
    }
}
