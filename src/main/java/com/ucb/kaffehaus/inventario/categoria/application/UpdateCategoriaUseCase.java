package com.ucb.kaffehaus.inventario.categoria.application;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ucb.kaffehaus.inventario.categoria.application.dto.UpdateCategoriaRequest;
import com.ucb.kaffehaus.inventario.categoria.domain.Categoria;
import com.ucb.kaffehaus.inventario.categoria.domain.CategoriaRepository;

@Service
@Transactional
public class UpdateCategoriaUseCase {

    private final CategoriaRepository categoriaRepository;

    public UpdateCategoriaUseCase(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public Optional<Categoria> execute(UUID id, UpdateCategoriaRequest request) {
        request.validate();

        Categoria categoriaToUpdate = Categoria.restore(id, request.getNombre(), false);
        return this.categoriaRepository.update(id, categoriaToUpdate);
    }
}
