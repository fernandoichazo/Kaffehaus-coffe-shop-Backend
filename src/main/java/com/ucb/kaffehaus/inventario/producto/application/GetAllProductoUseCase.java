package com.ucb.kaffehaus.inventario.producto.application;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ucb.kaffehaus.inventario.producto.domain.Producto;
import com.ucb.kaffehaus.inventario.producto.domain.ProductoRepository;

@Service
@Transactional(readOnly = true)
public class GetAllProductoUseCase {

    private final ProductoRepository productoRepository;

    public GetAllProductoUseCase(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<Producto> execute() {
        return this.productoRepository.getAll();
    }
}
