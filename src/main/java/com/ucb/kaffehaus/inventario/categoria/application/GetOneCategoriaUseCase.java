package com.ucb.kaffehaus.inventario.categoria.application;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ucb.kaffehaus.inventario.categoria.domain.Categoria;
import com.ucb.kaffehaus.inventario.categoria.domain.CategoriaRepository;

@Service
@Transactional(readOnly = true)
public class GetOneCategoriaUseCase {

    private final CategoriaRepository categoriaRepository;

    public GetOneCategoriaUseCase(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public Optional<Categoria> execute(UUID id) {
        return this.categoriaRepository.findOne(id)
                .filter(categoria -> !categoria.isBorrado());
    }
}
