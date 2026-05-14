package com.ucb.kaffehaus.personal.entidad.application;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ucb.kaffehaus.personal.entidad.application.dto.UpdateEntidadRequest;
import com.ucb.kaffehaus.personal.entidad.domain.Entidad;
import com.ucb.kaffehaus.personal.entidad.domain.EntidadRepository;

@Service
@Transactional
public class UpdateEntidadUseCase {

    private final EntidadRepository entidadRepository;

    public UpdateEntidadUseCase(EntidadRepository entidadRepository) {
        this.entidadRepository = entidadRepository;
    }

    public Optional<Entidad> execute(int id, UpdateEntidadRequest request) {
        request.validate();

        Entidad entidadToUpdate = Entidad.restore(
                id,
                request.getNombre(),
                request.getNit(),
                request.getTelefono(),
                request.getDescripcion(),
                false);

        return this.entidadRepository.update(id, entidadToUpdate);
    }
}
