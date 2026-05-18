package com.ucb.kaffehaus.inventario.producto.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ucb.kaffehaus.inventario.categoria.domain.Categoria;
import com.ucb.kaffehaus.inventario.categoria.domain.CategoriaRepository;
import com.ucb.kaffehaus.inventario.producto.application.dto.CreateProductoRequest;
import com.ucb.kaffehaus.inventario.producto.domain.Producto;
import com.ucb.kaffehaus.inventario.producto.domain.ProductoRepository;
import com.ucb.kaffehaus.shared.application.error.CustomException;

@Service
@Transactional
public class CreateProductoUseCase {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;

    public CreateProductoUseCase(ProductoRepository productoRepository, CategoriaRepository categoriaRepository) {
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public Producto execute(CreateProductoRequest request) {
        request.validate();

        Categoria categoria = this.categoriaRepository.findOne(request.getCategoriaId())
                .orElseThrow(() -> CustomException.notFound("No se encontro categoria con id " + request.getCategoriaId()));

        Producto producto = Producto.create(
                request.getNombre(),
                request.getStock(),
                request.getPrecio(),
                request.getStockMinimo(),
                request.getUnidad(),
                categoria,
                request.isVendible());

        return this.productoRepository.save(producto);
    }
}
