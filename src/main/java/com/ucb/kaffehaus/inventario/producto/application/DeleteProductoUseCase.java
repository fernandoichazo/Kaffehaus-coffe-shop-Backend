package com.ucb.kaffehaus.inventario.producto.application;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ucb.kaffehaus.inventario.producto.domain.ProductoRepository;

@Service
@Transactional
public class DeleteProductoUseCase {

    private final ProductoRepository productoRepository;

    public DeleteProductoUseCase(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public boolean execute(UUID id) {
        return this.productoRepository.deleteOne(id);
    }
}
