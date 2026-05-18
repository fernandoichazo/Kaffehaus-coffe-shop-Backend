package com.ucb.kaffehaus.inventario.producto.infrastructure;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ucb.kaffehaus.inventario.producto.domain.Producto;
import com.ucb.kaffehaus.inventario.producto.domain.ProductoDatasource;
import com.ucb.kaffehaus.inventario.producto.domain.ProductoRepository;

@Service
public class ProductoRepositoryImpl implements ProductoRepository {

    private final ProductoDatasource datasource;

    public ProductoRepositoryImpl(ProductoDatasource datasource) {
        this.datasource = datasource;
    }

    @Override
    public Producto save(Producto producto) {
        return this.datasource.save(producto);
    }

    @Override
    public Optional<Producto> update(UUID id, Producto producto) {
        return this.datasource.update(id, producto);
    }

    @Override
    public List<Producto> getAll() {
        return this.datasource.getAll();
    }

    @Override
    public Optional<Producto> findOne(UUID id) {
        return this.datasource.findOne(id);
    }

    @Override
    public boolean deleteOne(UUID id) {
        return this.datasource.deleteOne(id);
    }
}
