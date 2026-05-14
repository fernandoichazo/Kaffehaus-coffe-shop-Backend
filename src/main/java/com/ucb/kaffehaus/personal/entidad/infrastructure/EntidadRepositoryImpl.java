package com.ucb.kaffehaus.personal.entidad.infrastructure;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ucb.kaffehaus.personal.entidad.domain.Entidad;
import com.ucb.kaffehaus.personal.entidad.domain.EntidadDatasource;
import com.ucb.kaffehaus.personal.entidad.domain.EntidadRepository;

@Service
public class EntidadRepositoryImpl implements EntidadRepository {

	private final EntidadDatasource datasource;

	public EntidadRepositoryImpl(EntidadDatasource datasource) {
		this.datasource = datasource;
	}

	@Override
	public Entidad save(Entidad entidad) {
		return this.datasource.save(entidad);
	}

	@Override
	public Optional<Entidad> update(UUID Id, Entidad entidad) {
		return this.datasource.update(Id, entidad);
	}

	@Override
	public List<Entidad> getAll() {
		return this.datasource.getAll();
	}

	@Override
	public Optional<Entidad> findOne(UUID Id) {
		return this.datasource.findOne(Id);
	}

	@Override
	public boolean deleteOne(UUID Id) {
		return this.datasource.deleteOne(Id);
	}
}
