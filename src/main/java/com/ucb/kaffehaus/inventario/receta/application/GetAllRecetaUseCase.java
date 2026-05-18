package com.ucb.kaffehaus.inventario.receta.application;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ucb.kaffehaus.inventario.receta.domain.Receta;
import com.ucb.kaffehaus.inventario.receta.domain.RecetaRepository;

@Service
@Transactional(readOnly = true)
public class GetAllRecetaUseCase {

    private final RecetaRepository recetaRepository;

    public GetAllRecetaUseCase(RecetaRepository recetaRepository) {
        this.recetaRepository = recetaRepository;
    }

    public List<Receta> execute() {
        return this.recetaRepository.getAll();
    }
}
