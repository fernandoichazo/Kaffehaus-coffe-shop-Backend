package com.ucb.kaffehaus.inventario.producto.application;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ucb.kaffehaus.inventario.categoria.domain.Categoria;
import com.ucb.kaffehaus.inventario.categoria.domain.CategoriaRepository;
import com.ucb.kaffehaus.inventario.producto.application.dto.UpdateProductoRequest;
import com.ucb.kaffehaus.inventario.producto.domain.Producto;
import com.ucb.kaffehaus.inventario.producto.domain.ProductoRepository;
import com.ucb.kaffehaus.shared.application.error.CustomException;

@Service
@Transactional
public class UpdateProductoUseCase {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;

    public UpdateProductoUseCase(ProductoRepository productoRepository, CategoriaRepository categoriaRepository) {
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public Optional<Producto> execute(UUID id, UpdateProductoRequest request) {
        request.validate();

        Categoria categoria = null;
        if (request.getCategoriaId() != null) {
            categoria = this.categoriaRepository.findOne(request.getCategoriaId())
                    .orElseThrow(() -> CustomException.notFound("No se encontro categoria con id " + request.getCategoriaId()));
        }

        Producto productoToUpdate = Producto.restore(
                id,
                request.getNombre(),
                request.getStock(),
                request.getPrecio(),
                request.getStockMinimo(),
                request.getUnidad(),
                categoria,
                request.isVendible(),
                false);

        return this.productoRepository.update(id, productoToUpdate);
    }
}
