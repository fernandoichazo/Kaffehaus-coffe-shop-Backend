package com.ucb.kaffehaus.inventario.producto.presentation;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ucb.kaffehaus.inventario.producto.application.CreateProductoUseCase;
import com.ucb.kaffehaus.inventario.producto.application.DeleteProductoUseCase;
import com.ucb.kaffehaus.inventario.producto.application.GetAllProductoUseCase;
import com.ucb.kaffehaus.inventario.producto.application.GetOneProductoUseCase;
import com.ucb.kaffehaus.inventario.producto.application.UpdateProductoUseCase;
import com.ucb.kaffehaus.inventario.producto.application.dto.CreateProductoRequest;
import com.ucb.kaffehaus.inventario.producto.application.dto.ProductoResponse;
import com.ucb.kaffehaus.inventario.producto.application.dto.UpdateProductoRequest;
import com.ucb.kaffehaus.inventario.producto.domain.Producto;

@RestController
@RequestMapping("/api/v1/producto")
public class ProductoController {

    private final CreateProductoUseCase createProductoUseCase;
    private final UpdateProductoUseCase updateProductoUseCase;
    private final GetAllProductoUseCase getAllProductoUseCase;
    private final GetOneProductoUseCase getOneProductoUseCase;
    private final DeleteProductoUseCase deleteProductoUseCase;

    public ProductoController(
            CreateProductoUseCase createProductoUseCase,
            UpdateProductoUseCase updateProductoUseCase,
            GetAllProductoUseCase getAllProductoUseCase,
            GetOneProductoUseCase getOneProductoUseCase,
            DeleteProductoUseCase deleteProductoUseCase) {
        this.createProductoUseCase = createProductoUseCase;
        this.updateProductoUseCase = updateProductoUseCase;
        this.getAllProductoUseCase = getAllProductoUseCase;
        this.getOneProductoUseCase = getOneProductoUseCase;
        this.deleteProductoUseCase = deleteProductoUseCase;
    }

    @PostMapping
    public ResponseEntity<ProductoResponse> createProducto(@RequestBody CreateProductoRequest request) {
        request.validate();
        Producto producto = this.createProductoUseCase.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ProductoResponse.from(producto));
    }

    @GetMapping
    public ResponseEntity<List<ProductoResponse>> getAllProducto() {
        List<ProductoResponse> productos = this.getAllProductoUseCase.execute().stream()
                .map(ProductoResponse::from)
                .toList();
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponse> getOneProducto(@PathVariable UUID id) {
        Producto producto = this.getOneProductoUseCase.execute(id).get();
        return ResponseEntity.ok(ProductoResponse.from(producto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponse> updateProducto(@PathVariable UUID id, @RequestBody UpdateProductoRequest request) {
        Producto producto = this.updateProductoUseCase.execute(id, request).get();
        return ResponseEntity.ok(ProductoResponse.from(producto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProducto(@PathVariable UUID id) {
        boolean deleted = this.deleteProductoUseCase.execute(id);
        return ResponseEntity.ok(deleted);
    }
}
