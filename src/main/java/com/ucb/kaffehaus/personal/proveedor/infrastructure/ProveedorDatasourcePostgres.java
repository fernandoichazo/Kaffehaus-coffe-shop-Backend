package com.ucb.kaffehaus.personal.proveedor.infrastructure;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ucb.kaffehaus.personal.entidad.domain.Entidad;
import com.ucb.kaffehaus.personal.entidad.infrastructure.EntidadEntity;
import com.ucb.kaffehaus.personal.entidad.infrastructure.EntidadJpaRepository;
import com.ucb.kaffehaus.personal.persona.domain.Persona;
import com.ucb.kaffehaus.personal.persona.infrastructure.PersonaEntity;
import com.ucb.kaffehaus.personal.persona.infrastructure.PersonaJpaRepository;
import com.ucb.kaffehaus.personal.proveedor.domain.Proveedor;
import com.ucb.kaffehaus.personal.proveedor.domain.ProveedorDatasource;
import com.ucb.kaffehaus.shared.application.error.CustomException;

@Service
public class ProveedorDatasourcePostgres implements ProveedorDatasource {

    private final ProveedorJpaRepository proveedorJpaRepository;
    private final PersonaJpaRepository personaJpaRepository;
    private final EntidadJpaRepository entidadJpaRepository;

    public ProveedorDatasourcePostgres(
            ProveedorJpaRepository proveedorJpaRepository,
            PersonaJpaRepository personaJpaRepository,
            EntidadJpaRepository entidadJpaRepository) {
        this.proveedorJpaRepository = proveedorJpaRepository;
        this.personaJpaRepository = personaJpaRepository;
        this.entidadJpaRepository = entidadJpaRepository;
    }

    @Override
    public Proveedor save(Proveedor proveedor) {
        PersonaEntity personaEntity = this.getPersonaEntity(proveedor.getPersona().getId());
        EntidadEntity entidadEntity = this.getEntidadEntity(proveedor.getEntidad());

        ProveedorEntity entity = new ProveedorEntity(personaEntity, entidadEntity);
        entity.setBorrado(proveedor.isBorrado());
        ProveedorEntity saved = this.proveedorJpaRepository.save(entity);
        proveedor.setId(saved.getId());
        return proveedor;
    }

    @Override
    public Optional<Proveedor> update(int Id, Proveedor proveedor) {
        ProveedorEntity entity = this.proveedorJpaRepository.findById(Id)
                .orElseThrow(() -> CustomException.notFound("No se encontro proveedor con id " + Id));

        if (entity.isBorrado()) {
            throw CustomException.notFound("No se encontro proveedor con id " + Id);
        }

        if (proveedor.getPersona() != null && proveedor.getPersona().getId() != null) {
            PersonaEntity personaEntity = this.getPersonaEntity(proveedor.getPersona().getId());
            entity.setPersona(personaEntity);
        }

        if (proveedor.getEntidad() != null && proveedor.getEntidad().getId() != null) {
            EntidadEntity entidadEntity = this.getEntidadEntity(proveedor.getEntidad());
            entity.setEntidad(entidadEntity);
        }

        entity.setBorrado(proveedor.isBorrado());
        ProveedorEntity updated = this.proveedorJpaRepository.save(entity);
        return Optional.of(this.toDomain(updated));
    }

    @Override
    public List<Proveedor> getAll() {
        return this.proveedorJpaRepository.findAll().stream()
                .filter(entity -> !entity.isBorrado())
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Proveedor> findOne(int Id) {
        ProveedorEntity entity = this.proveedorJpaRepository.findById(Id)
                .orElseThrow(() -> CustomException.notFound("No se encontro proveedor con id " + Id));

        if (entity.isBorrado()) {
            throw CustomException.notFound("No se encontro proveedor con id " + Id);
        }

        return Optional.of(this.toDomain(entity));
    }

    @Override
    public boolean deleteOne(int Id) {
        ProveedorEntity entity = this.proveedorJpaRepository.findById(Id)
                .orElseThrow(() -> CustomException.notFound("No se encontro proveedor con id " + Id));

        if (entity.isBorrado()) {
            throw CustomException.notFound("No se encontro proveedor con id " + Id);
        }

        entity.setBorrado(true);
        this.proveedorJpaRepository.save(entity);
        return true;
    }

    private Proveedor toDomain(ProveedorEntity entity) {
        PersonaEntity personaEntity = entity.getPersona();
        Persona persona = Persona.restore(
                personaEntity.getId(),
                personaEntity.getNombre(),
                personaEntity.getApellidos(),
                personaEntity.getTelefono(),
                personaEntity.getDni(),
                personaEntity.isBorrado());

        Entidad entidad = null;
        if (entity.getEntidad() != null) {
            EntidadEntity entidadEntity = entity.getEntidad();
            entidad = Entidad.restore(
                    entidadEntity.getId(),
                    entidadEntity.getNombre(),
                    entidadEntity.getNit(),
                    entidadEntity.getTelefono(),
                    entidadEntity.getDescripcion(),
                    entidadEntity.isBorrado());
        }

        return Proveedor.restore(entity.getId(), persona, entidad, entity.isBorrado());
    }

    private PersonaEntity getPersonaEntity(Integer personaId) {
        PersonaEntity personaEntity = this.personaJpaRepository.findById(personaId)
                .orElseThrow(() -> CustomException.notFound("No se encontro persona con id " + personaId));

        if (personaEntity.isBorrado()) {
            throw CustomException.notFound("No se encontro persona con id " + personaId);
        }

        return personaEntity;
    }

    private EntidadEntity getEntidadEntity(Entidad entidad) {
        if (entidad == null || entidad.getId() == null) {
            return null;
        }

        EntidadEntity entidadEntity = this.entidadJpaRepository.findById(entidad.getId())
                .orElseThrow(() -> CustomException.notFound("No se encontro entidad con id " + entidad.getId()));

        if (entidadEntity.isBorrado()) {
            throw CustomException.notFound("No se encontro entidad con id " + entidad.getId());
        }

        return entidadEntity;
    }
}
