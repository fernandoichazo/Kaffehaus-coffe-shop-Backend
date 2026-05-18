package com.ucb.kaffehaus.inventario.receta.application;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ucb.kaffehaus.inventario.receta.domain.RecetaRepository;

@Service
@Transactional
public class DeleteRecetaUseCase {

    private final RecetaRepository recetaRepository;

    public DeleteRecetaUseCase(RecetaRepository recetaRepository) {
        this.recetaRepository = recetaRepository;
    }

    public boolean execute(UUID id) {
        return this.recetaRepository.deleteOne(id);
    }
}
