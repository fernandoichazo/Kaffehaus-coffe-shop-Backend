package com.ucb.kaffehaus.personal.rol.infrastructure;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ucb.kaffehaus.personal.rol.domain.Rol;
import com.ucb.kaffehaus.personal.rol.domain.RolDatasource;
import com.ucb.kaffehaus.shared.application.error.CustomException;

@Service
public class RolDatasourcePostgres implements RolDatasource {

    private final RolJpaRepository rolJpaRepository;

    public RolDatasourcePostgres(RolJpaRepository rolJpaRepository) {
        this.rolJpaRepository = rolJpaRepository;
    }

    @Override
    public Rol save(Rol rol) {
        RolEntity entity = new RolEntity(rol.getNombre());
        entity.setBorrado(rol.isBorrado());
        RolEntity saved = this.rolJpaRepository.save(entity);
        rol.setId(saved.getId());
        return rol;
    }

    @Override
    public Optional<Rol> update(int Id, Rol rol) {
        RolEntity entity = this.rolJpaRepository.findById(Id)
                .orElseThrow(() -> CustomException.notFound("No se encontro rol con id " + Id));

        if (entity.isBorrado()) {
            throw CustomException.notFound("No se encontro rol con id " + Id);
        }

        entity.setNombre(rol.getNombre());
        entity.setBorrado(rol.isBorrado());
        RolEntity updated = this.rolJpaRepository.save(entity);
        return Optional.of(this.toDomain(updated));
    }

    @Override
    public List<Rol> getAll() {
        return this.rolJpaRepository.findAll().stream()
                .filter(entity -> !entity.isBorrado())
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Rol> findOne(int Id) {
        RolEntity entity = this.rolJpaRepository.findById(Id)
                .orElseThrow(() -> CustomException.notFound("No se encontro rol con id " + Id));

        if (entity.isBorrado()) {
            throw CustomException.notFound("No se encontro rol con id " + Id);
        }

        return Optional.of(this.toDomain(entity));
    }

    @Override
    public boolean deleteOne(int Id) {
        RolEntity entity = this.rolJpaRepository.findById(Id)
                .orElseThrow(() -> CustomException.notFound("No se encontro rol con id " + Id));

        if (entity.isBorrado()) {
            throw CustomException.notFound("No se encontro rol con id " + Id);
        }

        entity.setBorrado(true);
        this.rolJpaRepository.save(entity);
        return true;
    }

    private Rol toDomain(RolEntity entity) {
        return Rol.restore(entity.getId(), entity.getNombre(), entity.isBorrado());
    }
}
