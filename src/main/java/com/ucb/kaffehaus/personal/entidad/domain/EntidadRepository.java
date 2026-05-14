package com.ucb.kaffehaus.personal.entidad.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EntidadRepository {
	Entidad save(Entidad entidad);
	Optional<Entidad> update(UUID Id, Entidad entidad);
	List<Entidad> getAll();
	Optional<Entidad> findOne(UUID Id);
	boolean deleteOne(UUID Id);
}
