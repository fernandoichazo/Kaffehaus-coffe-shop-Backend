package com.ucb.kaffehaus.personal.entidad.infrastructure;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ucb.kaffehaus.personal.entidad.domain.Entidad;
import com.ucb.kaffehaus.personal.entidad.domain.EntidadDatasource;
import com.ucb.kaffehaus.shared.application.error.CustomException;

@Service
public class EntidadDatasourcePostgres implements EntidadDatasource{

	private final EntidadJpaRepository entidadJpaRepository;

	public EntidadDatasourcePostgres(EntidadJpaRepository entidadJpaRepository) {
		this.entidadJpaRepository = entidadJpaRepository;
	}

	@Override
	public Entidad save(Entidad entidad) {
		EntidadEntity entity = this.toPersistence(entidad);
		EntidadEntity saved = this.entidadJpaRepository.save(entity);
		entidad.setId(saved.getId());
		return entidad;
	}

	@Override
	public Optional<Entidad> update(UUID Id, Entidad entidad) {
		EntidadEntity entity = this.entidadJpaRepository.findById(Id)
				.orElseThrow(() -> CustomException.notFound("No se encontro entidad con id " + Id));

		if (entity.isBorrado()) {
			throw CustomException.notFound("No se encontro entidad con id " + Id);
		}

		entity.setNombre(entidad.getNombre());
		entity.setNit(entidad.getNit());
		entity.setTelefono(entidad.getTelefono());
		entity.setDescripcion(entidad.getDescripcion());
		entity.setBorrado(entidad.isBorrado());

		EntidadEntity updated = this.entidadJpaRepository.save(entity);
		return Optional.of(this.toDomain(updated));
	}

	@Override
	public List<Entidad> getAll() {
		return this.entidadJpaRepository.findAll().stream()
				.filter(entity -> !entity.isBorrado())
				.map(this::toDomain)
				.collect(Collectors.toList());
	}

	@Override
	public Optional<Entidad> findOne(UUID Id) {
		EntidadEntity entity = this.entidadJpaRepository.findById(Id)
				.orElseThrow(() -> CustomException.notFound("No se encontro entidad con id " + Id));

		if (entity.isBorrado()) {
			throw CustomException.notFound("No se encontro entidad con id " + Id);
		}

		return Optional.of(this.toDomain(entity));
	}

	@Override
	public boolean deleteOne(UUID Id) {
		EntidadEntity entity = this.entidadJpaRepository.findById(Id)
				.orElseThrow(() -> CustomException.notFound("No se encontro entidad con id " + Id));

		if (entity.isBorrado()) {
			throw CustomException.notFound("No se encontro entidad con id " + Id);
		}

		entity.setBorrado(true);
		this.entidadJpaRepository.save(entity);
		return true;
	}

	private Entidad toDomain(EntidadEntity entity) {
		return Entidad.restore(
				entity.getId(),
				entity.getNombre(),
				entity.getNit(),
				entity.getTelefono(),
				entity.getDescripcion(),
				entity.isBorrado());
	}

	private EntidadEntity toPersistence(Entidad entidad) {
		EntidadEntity entity = new EntidadEntity(
				entidad.getNombre(),
				entidad.getNit(),
				entidad.getTelefono(),
				entidad.getDescripcion());
		entity.setBorrado(entidad.isBorrado());
		return entity;
	}
}
