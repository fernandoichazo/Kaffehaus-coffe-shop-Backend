package com.ucb.kaffehaus.inventario.ingredientesReceta.infrastructure;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ucb.kaffehaus.inventario.ingredientesReceta.domain.IngredientesReceta;
import com.ucb.kaffehaus.inventario.ingredientesReceta.domain.IngredientesRecetaDatasource;
import com.ucb.kaffehaus.inventario.producto.infrastructure.ProductoEntity;
import com.ucb.kaffehaus.inventario.producto.infrastructure.ProductoJpaRepository;
import com.ucb.kaffehaus.inventario.receta.infrastructure.RecetaEntity;
import com.ucb.kaffehaus.inventario.receta.infrastructure.RecetaJpaRepository;
import com.ucb.kaffehaus.shared.application.error.CustomException;

@Service
public class IngredientesRecetaDatasourcePostgres implements IngredientesRecetaDatasource {

    private final IngredientesRecetaJpaRepository ingredientesRecetaJpaRepository;
    private final RecetaJpaRepository recetaJpaRepository;
    private final ProductoJpaRepository productoJpaRepository;

    public IngredientesRecetaDatasourcePostgres(
            IngredientesRecetaJpaRepository ingredientesRecetaJpaRepository,
            RecetaJpaRepository recetaJpaRepository,
            ProductoJpaRepository productoJpaRepository) {
        this.ingredientesRecetaJpaRepository = ingredientesRecetaJpaRepository;
        this.recetaJpaRepository = recetaJpaRepository;
        this.productoJpaRepository = productoJpaRepository;
    }

    @Override
    public IngredientesReceta save(IngredientesReceta ingredientesReceta) {
        RecetaEntity recetaEntity = this.getRecetaEntity(ingredientesReceta.getRecetaId());
        ProductoEntity productoEntity = this.getProductoEntity(ingredientesReceta.getProductoId());

        IngredientesRecetaEntity entity = new IngredientesRecetaEntity(recetaEntity, productoEntity, ingredientesReceta.getCantidad());
        entity.setBorrado(ingredientesReceta.isBorrado());

        IngredientesRecetaEntity saved = this.ingredientesRecetaJpaRepository.save(entity);
        ingredientesReceta.setId(saved.getId());
        return ingredientesReceta;
    }

    @Override
    public Optional<IngredientesReceta> update(UUID id, IngredientesReceta ingredientesReceta) {
        IngredientesRecetaEntity entity = this.ingredientesRecetaJpaRepository.findById(id)
                .orElseThrow(() -> CustomException.notFound("No se encontro ingrediente receta con id " + id));

        if (entity.isBorrado()) {
            throw CustomException.notFound("No se encontro ingrediente receta con id " + id);
        }

        if (ingredientesReceta.getRecetaId() != null) {
            RecetaEntity recetaEntity = this.getRecetaEntity(ingredientesReceta.getRecetaId());
            entity.setReceta(recetaEntity);
        }
        if (ingredientesReceta.getProductoId() != null) {
            ProductoEntity productoEntity = this.getProductoEntity(ingredientesReceta.getProductoId());
            entity.setProducto(productoEntity);
        }
        if (ingredientesReceta.getCantidad() != null) {
            entity.setCantidad(ingredientesReceta.getCantidad());
        }
        entity.setBorrado(ingredientesReceta.isBorrado());

        IngredientesRecetaEntity updated = this.ingredientesRecetaJpaRepository.save(entity);
        return Optional.of(this.toDomain(updated));
    }

    @Override
    public List<IngredientesReceta> getAll() {
        return this.ingredientesRecetaJpaRepository.findAll().stream()
                .filter(entity -> !entity.isBorrado())
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<IngredientesReceta> findOne(UUID id) {
        IngredientesRecetaEntity entity = this.ingredientesRecetaJpaRepository.findById(id)
                .orElseThrow(() -> CustomException.notFound("No se encontro ingrediente receta con id " + id));

        if (entity.isBorrado()) {
            throw CustomException.notFound("No se encontro ingrediente receta con id " + id);
        }

        return Optional.of(this.toDomain(entity));
    }

    @Override
    public boolean deleteOne(UUID id) {
        IngredientesRecetaEntity entity = this.ingredientesRecetaJpaRepository.findById(id)
                .orElseThrow(() -> CustomException.notFound("No se encontro ingrediente receta con id " + id));

        if (entity.isBorrado()) {
            throw CustomException.notFound("No se encontro ingrediente receta con id " + id);
        }

        entity.setBorrado(true);
        this.ingredientesRecetaJpaRepository.save(entity);
        return true;
    }

    private IngredientesReceta toDomain(IngredientesRecetaEntity entity) {
        return IngredientesReceta.restore(
                entity.getId(),
                entity.getReceta().getId(),
                entity.getProducto().getId(),
                entity.getCantidad(),
                entity.isBorrado());
    }

    private RecetaEntity getRecetaEntity(UUID recetaId) {
        RecetaEntity recetaEntity = this.recetaJpaRepository.findById(recetaId)
                .orElseThrow(() -> CustomException.notFound("No se encontro receta con id " + recetaId));

        if (recetaEntity.isBorrado()) {
            throw CustomException.notFound("No se encontro receta con id " + recetaId);
        }

        return recetaEntity;
    }

    private ProductoEntity getProductoEntity(UUID productoId) {
        ProductoEntity productoEntity = this.productoJpaRepository.findById(productoId)
                .orElseThrow(() -> CustomException.notFound("No se encontro producto con id " + productoId));

        if (productoEntity.isBorrado()) {
            throw CustomException.notFound("No se encontro producto con id " + productoId);
        }

        return productoEntity;
    }
}
