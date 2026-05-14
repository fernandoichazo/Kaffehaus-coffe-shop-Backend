package com.ucb.kaffehaus.personal.entidad.infrastructure;

import java.util.List;
import java.util.Optional;

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
	public Optional<Entidad> update(int Id, Entidad entidad) {
		return this.datasource.update(Id, entidad);
	}

	@Override
	public List<Entidad> getAll() {
		return this.datasource.getAll();
	}

	@Override
	public Optional<Entidad> findOne(int Id) {
		return this.datasource.findOne(Id);
	}

	@Override
	public boolean deleteOne(int Id) {
		return this.datasource.deleteOne(Id);
	}
}
