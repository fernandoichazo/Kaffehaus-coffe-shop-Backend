package com.ucb.kaffehaus.inventario.categoria.infrastructure;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ucb.kaffehaus.inventario.categoria.domain.Categoria;
import com.ucb.kaffehaus.inventario.categoria.domain.CategoriaDatasource;
import com.ucb.kaffehaus.shared.application.error.CustomException;

@Service
public class CategoriaDatasourcePostgres implements CategoriaDatasource {

    private final CategoriaJpaRepository categoriaJpaRepository;

    public CategoriaDatasourcePostgres(CategoriaJpaRepository categoriaJpaRepository) {
        this.categoriaJpaRepository = categoriaJpaRepository;
    }

    @Override
    public Categoria save(Categoria categoria) {
        CategoriaEntity entity = new CategoriaEntity(categoria.getNombre());
        entity.setBorrado(categoria.isBorrado());
        CategoriaEntity saved = this.categoriaJpaRepository.save(entity);
        categoria.setId(saved.getId());
        return categoria;
    }

    @Override
    public Optional<Categoria> update(UUID id, Categoria categoria) {
        CategoriaEntity entity = this.categoriaJpaRepository.findById(id)
                .orElseThrow(() -> CustomException.notFound("No se encontro categoria con id " + id));

        if (entity.isBorrado()) {
            throw CustomException.notFound("No se encontro categoria con id " + id);
        }

        entity.setNombre(categoria.getNombre());
        entity.setBorrado(categoria.isBorrado());

        CategoriaEntity updated = this.categoriaJpaRepository.save(entity);
        return Optional.of(this.toDomain(updated));
    }

    @Override
    public List<Categoria> getAll() {
        return this.categoriaJpaRepository.findAll().stream()
                .filter(entity -> !entity.isBorrado())
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Categoria> findOne(UUID id) {
        CategoriaEntity entity = this.categoriaJpaRepository.findById(id)
                .orElseThrow(() -> CustomException.notFound("No se encontro categoria con id " + id));

        if (entity.isBorrado()) {
            throw CustomException.notFound("No se encontro categoria con id " + id);
        }

        return Optional.of(this.toDomain(entity));
    }

    @Override
    public boolean deleteOne(UUID id) {
        CategoriaEntity entity = this.categoriaJpaRepository.findById(id)
                .orElseThrow(() -> CustomException.notFound("No se encontro categoria con id " + id));

        if (entity.isBorrado()) {
            throw CustomException.notFound("No se encontro categoria con id " + id);
        }

        entity.setBorrado(true);
        this.categoriaJpaRepository.save(entity);
        return true;
    }

    private Categoria toDomain(CategoriaEntity entity) {
        return Categoria.restore(entity.getId(), entity.getNombre(), entity.isBorrado());
    }
}
