package com.ucb.kaffehaus.inventario.lote.infrastructure;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ucb.kaffehaus.inventario.lote.domain.Lote;
import com.ucb.kaffehaus.inventario.lote.domain.LoteDatasource;
import com.ucb.kaffehaus.personal.proveedor.infrastructure.ProveedorEntity;
import com.ucb.kaffehaus.personal.proveedor.infrastructure.ProveedorJpaRepository;
import com.ucb.kaffehaus.shared.application.error.CustomException;

@Service
public class LoteDatasourcePostgres implements LoteDatasource {

    private final LoteJpaRepository loteJpaRepository;
    private final ProveedorJpaRepository proveedorJpaRepository;

    public LoteDatasourcePostgres(LoteJpaRepository loteJpaRepository, ProveedorJpaRepository proveedorJpaRepository) {
        this.loteJpaRepository = loteJpaRepository;
        this.proveedorJpaRepository = proveedorJpaRepository;
    }

    @Override
    public Lote save(Lote lote) {
        ProveedorEntity proveedorEntity = this.getProveedorEntity(lote.getProveedorId());

        LoteEntity entity = new LoteEntity(lote.getFechaCompra(), proveedorEntity, lote.getTotal());
        entity.setBorrado(lote.isBorrado());
        LoteEntity saved = this.loteJpaRepository.save(entity);
        lote.setId(saved.getId());
        return lote;
    }

    @Override
    public Optional<Lote> update(UUID id, Lote lote) {
        LoteEntity entity = this.loteJpaRepository.findById(id)
                .orElseThrow(() -> CustomException.notFound("No se encontro lote con id " + id));

        if (entity.isBorrado()) {
            throw CustomException.notFound("No se encontro lote con id " + id);
        }

        if (lote.getFechaCompra() != null) {
            entity.setFechaCompra(lote.getFechaCompra());
        }
        if (lote.getProveedorId() != null) {
            ProveedorEntity proveedorEntity = this.getProveedorEntity(lote.getProveedorId());
            entity.setProveedor(proveedorEntity);
        }
        if (lote.getTotal() != null) {
            entity.setTotal(lote.getTotal());
        }
        entity.setBorrado(lote.isBorrado());

        LoteEntity updated = this.loteJpaRepository.save(entity);
        return Optional.of(this.toDomain(updated));
    }

    @Override
    public List<Lote> getAll() {
        return this.loteJpaRepository.findAll().stream()
                .filter(entity -> !entity.isBorrado())
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Lote> findOne(UUID id) {
        LoteEntity entity = this.loteJpaRepository.findById(id)
                .orElseThrow(() -> CustomException.notFound("No se encontro lote con id " + id));

        if (entity.isBorrado()) {
            throw CustomException.notFound("No se encontro lote con id " + id);
        }

        return Optional.of(this.toDomain(entity));
    }

    @Override
    public boolean deleteOne(UUID id) {
        LoteEntity entity = this.loteJpaRepository.findById(id)
                .orElseThrow(() -> CustomException.notFound("No se encontro lote con id " + id));

        if (entity.isBorrado()) {
            throw CustomException.notFound("No se encontro lote con id " + id);
        }

        entity.setBorrado(true);
        this.loteJpaRepository.save(entity);
        return true;
    }

    private Lote toDomain(LoteEntity entity) {
        return Lote.restore(
                entity.getId(),
                entity.getFechaCompra(),
                entity.getProveedor().getId(),
                entity.getTotal(),
                entity.isBorrado());
    }

    private ProveedorEntity getProveedorEntity(UUID proveedorId) {
        ProveedorEntity proveedorEntity = this.proveedorJpaRepository.findById(proveedorId)
                .orElseThrow(() -> CustomException.notFound("No se encontro proveedor con id " + proveedorId));

        if (proveedorEntity.isBorrado()) {
            throw CustomException.notFound("No se encontro proveedor con id " + proveedorId);
        }

        return proveedorEntity;
    }
}
