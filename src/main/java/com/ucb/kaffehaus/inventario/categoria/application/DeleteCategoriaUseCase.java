package com.ucb.kaffehaus.inventario.categoria.application;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ucb.kaffehaus.inventario.categoria.domain.CategoriaRepository;

@Service
@Transactional
public class DeleteCategoriaUseCase {

    private final CategoriaRepository categoriaRepository;

    public DeleteCategoriaUseCase(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public boolean execute(UUID id) {
        return this.categoriaRepository.deleteOne(id);
    }
}
