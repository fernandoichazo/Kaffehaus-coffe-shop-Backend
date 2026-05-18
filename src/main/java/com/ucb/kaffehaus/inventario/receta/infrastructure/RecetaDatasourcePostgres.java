package com.ucb.kaffehaus.inventario.receta.infrastructure;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ucb.kaffehaus.inventario.producto.infrastructure.ProductoEntity;
import com.ucb.kaffehaus.inventario.producto.infrastructure.ProductoJpaRepository;
import com.ucb.kaffehaus.inventario.receta.domain.Receta;
import com.ucb.kaffehaus.inventario.receta.domain.RecetaDatasource;
import com.ucb.kaffehaus.shared.application.error.CustomException;

@Service
public class RecetaDatasourcePostgres implements RecetaDatasource {

    private final RecetaJpaRepository recetaJpaRepository;
    private final ProductoJpaRepository productoJpaRepository;

    public RecetaDatasourcePostgres(RecetaJpaRepository recetaJpaRepository, ProductoJpaRepository productoJpaRepository) {
        this.recetaJpaRepository = recetaJpaRepository;
        this.productoJpaRepository = productoJpaRepository;
    }

    @Override
    public Receta save(Receta receta) {
        ProductoEntity productoEntity = this.getProductoEntity(receta.getProductoId());

        RecetaEntity entity = new RecetaEntity(productoEntity, receta.getCantidad());
        entity.setBorrado(receta.isBorrado());
        RecetaEntity saved = this.recetaJpaRepository.save(entity);
        receta.setId(saved.getId());
        return receta;
    }

    @Override
    public Optional<Receta> update(UUID id, Receta receta) {
        RecetaEntity entity = this.recetaJpaRepository.findById(id)
                .orElseThrow(() -> CustomException.notFound("No se encontro receta con id " + id));

        if (entity.isBorrado()) {
            throw CustomException.notFound("No se encontro receta con id " + id);
        }

        if (receta.getProductoId() != null) {
            ProductoEntity productoEntity = this.getProductoEntity(receta.getProductoId());
            entity.setProducto(productoEntity);
        }
        if (receta.getCantidad() != null) {
            entity.setCantidad(receta.getCantidad());
        }
        entity.setBorrado(receta.isBorrado());

        RecetaEntity updated = this.recetaJpaRepository.save(entity);
        return Optional.of(this.toDomain(updated));
    }

    @Override
    public List<Receta> getAll() {
        return this.recetaJpaRepository.findAll().stream()
                .filter(entity -> !entity.isBorrado())
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Receta> findOne(UUID id) {
        RecetaEntity entity = this.recetaJpaRepository.findById(id)
                .orElseThrow(() -> CustomException.notFound("No se encontro receta con id " + id));

        if (entity.isBorrado()) {
            throw CustomException.notFound("No se encontro receta con id " + id);
        }

        return Optional.of(this.toDomain(entity));
    }

    @Override
    public boolean deleteOne(UUID id) {
        RecetaEntity entity = this.recetaJpaRepository.findById(id)
                .orElseThrow(() -> CustomException.notFound("No se encontro receta con id " + id));

        if (entity.isBorrado()) {
            throw CustomException.notFound("No se encontro receta con id " + id);
        }

        entity.setBorrado(true);
        this.recetaJpaRepository.save(entity);
        return true;
    }

    private Receta toDomain(RecetaEntity entity) {
        return Receta.restore(
                entity.getId(),
                entity.getProducto().getId(),
                entity.getCantidad(),
                entity.isBorrado());
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
