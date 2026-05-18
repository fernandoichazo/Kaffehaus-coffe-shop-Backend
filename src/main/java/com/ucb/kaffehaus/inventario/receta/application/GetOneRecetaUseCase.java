package com.ucb.kaffehaus.inventario.receta.application;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ucb.kaffehaus.inventario.receta.domain.Receta;
import com.ucb.kaffehaus.inventario.receta.domain.RecetaRepository;

@Service
@Transactional(readOnly = true)
public class GetOneRecetaUseCase {

    private final RecetaRepository recetaRepository;

    public GetOneRecetaUseCase(RecetaRepository recetaRepository) {
        this.recetaRepository = recetaRepository;
    }

    public Optional<Receta> execute(UUID id) {
        return this.recetaRepository.findOne(id)
                .filter(receta -> !receta.isBorrado());
    }
}
