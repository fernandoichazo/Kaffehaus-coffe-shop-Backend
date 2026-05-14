package com.ucb.kaffehaus.personal.entidad.domain;

import java.util.List;
import java.util.Optional;

public interface EntidadRepository {
	Entidad save(Entidad entidad);
	Optional<Entidad> update(int Id, Entidad entidad);
	List<Entidad> getAll();
	Optional<Entidad> findOne(int Id);
	boolean deleteOne(int Id);
}
