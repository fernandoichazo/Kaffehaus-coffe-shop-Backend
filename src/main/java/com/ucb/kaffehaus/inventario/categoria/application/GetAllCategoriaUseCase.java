package com.ucb.kaffehaus.inventario.categoria.application;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ucb.kaffehaus.inventario.categoria.domain.Categoria;
import com.ucb.kaffehaus.inventario.categoria.domain.CategoriaRepository;

@Service
@Transactional(readOnly = true)
public class GetAllCategoriaUseCase {

    private final CategoriaRepository categoriaRepository;

    public GetAllCategoriaUseCase(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<Categoria> execute() {
        return this.categoriaRepository.getAll();
    }
}
