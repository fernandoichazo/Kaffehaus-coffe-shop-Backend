package com.ucb.kaffehaus.inventario.detalleLote.infrastructure;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ucb.kaffehaus.inventario.detalleLote.domain.DetalleLote;
import com.ucb.kaffehaus.inventario.detalleLote.domain.DetalleLoteDatasource;
import com.ucb.kaffehaus.inventario.lote.infrastructure.LoteEntity;
import com.ucb.kaffehaus.inventario.lote.infrastructure.LoteJpaRepository;
import com.ucb.kaffehaus.inventario.producto.infrastructure.ProductoEntity;
import com.ucb.kaffehaus.inventario.producto.infrastructure.ProductoJpaRepository;
import com.ucb.kaffehaus.shared.application.error.CustomException;

@Service
public class DetalleLoteDatasourcePostgres implements DetalleLoteDatasource {

    private final DetalleLoteJpaRepository detalleLoteJpaRepository;
    private final LoteJpaRepository loteJpaRepository;
    private final ProductoJpaRepository productoJpaRepository;

    public DetalleLoteDatasourcePostgres(
            DetalleLoteJpaRepository detalleLoteJpaRepository,
            LoteJpaRepository loteJpaRepository,
            ProductoJpaRepository productoJpaRepository) {
        this.detalleLoteJpaRepository = detalleLoteJpaRepository;
        this.loteJpaRepository = loteJpaRepository;
        this.productoJpaRepository = productoJpaRepository;
    }

    @Override
    public DetalleLote save(DetalleLote detalleLote) {
        LoteEntity loteEntity = this.getLoteEntity(detalleLote.getLoteId());
        ProductoEntity productoEntity = this.getProductoEntity(detalleLote.getProductoId());

        DetalleLoteEntity entity = new DetalleLoteEntity(
                loteEntity,
                productoEntity,
                detalleLote.getCantidad(),
                detalleLote.getPrecioUnitario(),
                detalleLote.getFechaAcabado());
        entity.setBorrado(detalleLote.isBorrado());

        DetalleLoteEntity saved = this.detalleLoteJpaRepository.save(entity);
        detalleLote.setId(saved.getId());
        return detalleLote;
    }

    @Override
    public Optional<DetalleLote> update(UUID id, DetalleLote detalleLote) {
        DetalleLoteEntity entity = this.detalleLoteJpaRepository.findById(id)
                .orElseThrow(() -> CustomException.notFound("No se encontro detalle lote con id " + id));

        if (entity.isBorrado()) {
            throw CustomException.notFound("No se encontro detalle lote con id " + id);
        }

        if (detalleLote.getLoteId() != null) {
            LoteEntity loteEntity = this.getLoteEntity(detalleLote.getLoteId());
            entity.setLote(loteEntity);
        }
        if (detalleLote.getProductoId() != null) {
            ProductoEntity productoEntity = this.getProductoEntity(detalleLote.getProductoId());
            entity.setProducto(productoEntity);
        }

        BigDecimal cantidad = detalleLote.getCantidad() != null ? detalleLote.getCantidad() : entity.getCantidad();
        BigDecimal precioUnitario = detalleLote.getPrecioUnitario() != null ? detalleLote.getPrecioUnitario() : entity.getPrecioUnitario();

        if (detalleLote.getCantidad() != null) {
            entity.setCantidad(detalleLote.getCantidad());
        }
        if (detalleLote.getPrecioUnitario() != null) {
            entity.setPrecioUnitario(detalleLote.getPrecioUnitario());
        }
        if (detalleLote.getFechaAcabado() != null) {
            entity.setFechaAcabado(detalleLote.getFechaAcabado());
        }

        entity.setSubtotal(cantidad.multiply(precioUnitario));
        entity.setBorrado(detalleLote.isBorrado());

        DetalleLoteEntity updated = this.detalleLoteJpaRepository.save(entity);
        return Optional.of(this.toDomain(updated));
    }

    @Override
    public List<DetalleLote> getAll() {
        return this.detalleLoteJpaRepository.findAll().stream()
                .filter(entity -> !entity.isBorrado())
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<DetalleLote> findOne(UUID id) {
        DetalleLoteEntity entity = this.detalleLoteJpaRepository.findById(id)
                .orElseThrow(() -> CustomException.notFound("No se encontro detalle lote con id " + id));

        if (entity.isBorrado()) {
            throw CustomException.notFound("No se encontro detalle lote con id " + id);
        }

        return Optional.of(this.toDomain(entity));
    }

    @Override
    public boolean deleteOne(UUID id) {
        DetalleLoteEntity entity = this.detalleLoteJpaRepository.findById(id)
                .orElseThrow(() -> CustomException.notFound("No se encontro detalle lote con id " + id));

        if (entity.isBorrado()) {
            throw CustomException.notFound("No se encontro detalle lote con id " + id);
        }

        entity.setBorrado(true);
        this.detalleLoteJpaRepository.save(entity);
        return true;
    }

    private DetalleLote toDomain(DetalleLoteEntity entity) {
        return DetalleLote.restore(
                entity.getId(),
                entity.getLote().getId(),
                entity.getProducto().getId(),
                entity.getCantidad(),
                entity.getPrecioUnitario(),
                entity.getSubtotal(),
                entity.getFechaAcabado(),
                entity.isBorrado());
    }

    private LoteEntity getLoteEntity(UUID loteId) {
        LoteEntity loteEntity = this.loteJpaRepository.findById(loteId)
                .orElseThrow(() -> CustomException.notFound("No se encontro lote con id " + loteId));

        if (loteEntity.isBorrado()) {
            throw CustomException.notFound("No se encontro lote con id " + loteId);
        }

        return loteEntity;
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
