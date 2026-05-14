package com.ucb.kaffehaus.personal.entidad.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ucb.kaffehaus.personal.entidad.application.dto.CreateEntidadRequest;
import com.ucb.kaffehaus.personal.entidad.domain.Entidad;
import com.ucb.kaffehaus.personal.entidad.domain.EntidadRepository;

@Service
@Transactional
public class CreateEntidadUseCase {

    private final EntidadRepository entidadRepository;

    public CreateEntidadUseCase(EntidadRepository entidadRepository) {
        this.entidadRepository = entidadRepository;
    }

    public Entidad execute(CreateEntidadRequest request) {
        request.validate();

        Entidad entidad = Entidad.create(
                request.getNombre(),
                request.getNit(),
                request.getTelefono(),
                request.getDescripcion());
        return this.entidadRepository.save(entidad);
    }
}
