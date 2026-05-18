package com.ucb.kaffehaus.inventario.producto.application;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ucb.kaffehaus.inventario.producto.domain.Producto;
import com.ucb.kaffehaus.inventario.producto.domain.ProductoRepository;

@Service
@Transactional(readOnly = true)
public class GetOneProductoUseCase {

    private final ProductoRepository productoRepository;

    public GetOneProductoUseCase(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public Optional<Producto> execute(UUID id) {
        return this.productoRepository.findOne(id)
                .filter(producto -> !producto.isBorrado());
    }
}
