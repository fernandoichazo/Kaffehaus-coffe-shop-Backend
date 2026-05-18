package com.ucb.kaffehaus.inventario.categoria.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ucb.kaffehaus.inventario.categoria.application.dto.CreateCategoriaRequest;
import com.ucb.kaffehaus.inventario.categoria.domain.Categoria;
import com.ucb.kaffehaus.inventario.categoria.domain.CategoriaRepository;

@Service
@Transactional
public class CreateCategoriaUseCase {

    private final CategoriaRepository categoriaRepository;

    public CreateCategoriaUseCase(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public Categoria execute(CreateCategoriaRequest request) {
        request.validate();
        Categoria categoria = Categoria.create(request.getNombre());
        return this.categoriaRepository.save(categoria);
    }
}
