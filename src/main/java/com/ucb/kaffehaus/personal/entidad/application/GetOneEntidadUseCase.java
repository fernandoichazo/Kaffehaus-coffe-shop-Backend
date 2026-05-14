package com.ucb.kaffehaus.personal.entidad.application;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ucb.kaffehaus.personal.entidad.domain.Entidad;
import com.ucb.kaffehaus.personal.entidad.domain.EntidadRepository;

@Service
@Transactional(readOnly = true)
public class GetOneEntidadUseCase {

    private final EntidadRepository entidadRepository;

    public GetOneEntidadUseCase(EntidadRepository entidadRepository) {
        this.entidadRepository = entidadRepository;
    }

    public Optional<Entidad> execute(int id) {
        return this.entidadRepository.findOne(id)
                .filter(entidad -> !entidad.isBorrado());
    }
}
