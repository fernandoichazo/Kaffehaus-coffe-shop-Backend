package com.ucb.kaffehaus.inventario.producto.infrastructure;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ucb.kaffehaus.inventario.categoria.domain.Categoria;
import com.ucb.kaffehaus.inventario.categoria.infrastructure.CategoriaEntity;
import com.ucb.kaffehaus.inventario.categoria.infrastructure.CategoriaJpaRepository;
import com.ucb.kaffehaus.inventario.producto.domain.Producto;
import com.ucb.kaffehaus.inventario.producto.domain.ProductoDatasource;
import com.ucb.kaffehaus.shared.application.error.CustomException;

@Service
public class ProductoDatasourcePostgres implements ProductoDatasource {

    private final ProductoJpaRepository productoJpaRepository;
    private final CategoriaJpaRepository categoriaJpaRepository;

    public ProductoDatasourcePostgres(
            ProductoJpaRepository productoJpaRepository,
            CategoriaJpaRepository categoriaJpaRepository) {
        this.productoJpaRepository = productoJpaRepository;
        this.categoriaJpaRepository = categoriaJpaRepository;
    }

    @Override
    public Producto save(Producto producto) {
        CategoriaEntity categoriaEntity = this.getCategoriaEntity(producto.getCategoria().getId());

        ProductoEntity entity = new ProductoEntity(
                producto.getNombre(),
                producto.getStock(),
                producto.getPrecio(),
                producto.getStockMinimo(),
                producto.getUnidad(),
                categoriaEntity,
                producto.isVendible());
        entity.setBorrado(producto.isBorrado());

        ProductoEntity saved = this.productoJpaRepository.save(entity);
        producto.setId(saved.getId());
        return producto;
    }

    @Override
    public Optional<Producto> update(UUID id, Producto producto) {
        ProductoEntity entity = this.productoJpaRepository.findById(id)
                .orElseThrow(() -> CustomException.notFound("No se encontro producto con id " + id));

        if (entity.isBorrado()) {
            throw CustomException.notFound("No se encontro producto con id " + id);
        }

        if (producto.getNombre() != null) {
            entity.setNombre(producto.getNombre());
        }
        if (producto.getStock() != null) {
            entity.setStock(producto.getStock());
        }
        if (producto.getPrecio() != null) {
            entity.setPrecio(producto.getPrecio());
        }
        if (producto.getStockMinimo() != null) {
            entity.setStockMinimo(producto.getStockMinimo());
        }
        if (producto.getUnidad() != null) {
            entity.setUnidad(producto.getUnidad());
        }
        if (producto.getCategoria() != null && producto.getCategoria().getId() != null) {
            CategoriaEntity categoriaEntity = this.getCategoriaEntity(producto.getCategoria().getId());
            entity.setCategoria(categoriaEntity);
        }
        entity.setVendible(producto.isVendible());
        entity.setBorrado(producto.isBorrado());

        ProductoEntity updated = this.productoJpaRepository.save(entity);
        return Optional.of(this.toDomain(updated));
    }

    @Override
    public List<Producto> getAll() {
        return this.productoJpaRepository.findAll().stream()
                .filter(entity -> !entity.isBorrado())
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Producto> findOne(UUID id) {
        ProductoEntity entity = this.productoJpaRepository.findById(id)
                .orElseThrow(() -> CustomException.notFound("No se encontro producto con id " + id));

        if (entity.isBorrado()) {
            throw CustomException.notFound("No se encontro producto con id " + id);
        }

        return Optional.of(this.toDomain(entity));
    }

    @Override
    public boolean deleteOne(UUID id) {
        ProductoEntity entity = this.productoJpaRepository.findById(id)
                .orElseThrow(() -> CustomException.notFound("No se encontro producto con id " + id));

        if (entity.isBorrado()) {
            throw CustomException.notFound("No se encontro producto con id " + id);
        }

        entity.setBorrado(true);
        this.productoJpaRepository.save(entity);
        return true;
    }

    private Producto toDomain(ProductoEntity entity) {
        CategoriaEntity categoriaEntity = entity.getCategoria();
        Categoria categoria = Categoria.restore(
                categoriaEntity.getId(),
                categoriaEntity.getNombre(),
                categoriaEntity.isBorrado());

        return Producto.restore(
                entity.getId(),
                entity.getNombre(),
                entity.getStock(),
                entity.getPrecio(),
                entity.getStockMinimo(),
                entity.getUnidad(),
                categoria,
                entity.isVendible(),
                entity.isBorrado());
    }

    private CategoriaEntity getCategoriaEntity(UUID categoriaId) {
        CategoriaEntity categoriaEntity = this.categoriaJpaRepository.findById(categoriaId)
                .orElseThrow(() -> CustomException.notFound("No se encontro categoria con id " + categoriaId));

        if (categoriaEntity.isBorrado()) {
            throw CustomException.notFound("No se encontro categoria con id " + categoriaId);
        }

        return categoriaEntity;
    }
}
