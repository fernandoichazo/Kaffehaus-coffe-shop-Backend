package com.ucb.kaffehaus.personal.entidad.application;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ucb.kaffehaus.personal.entidad.domain.Entidad;
import com.ucb.kaffehaus.personal.entidad.domain.EntidadRepository;

@Service
@Transactional(readOnly = true)
public class GetAllEntidadUseCase {

    private final EntidadRepository entidadRepository;

    public GetAllEntidadUseCase(EntidadRepository entidadRepository) {
        this.entidadRepository = entidadRepository;
    }

    public List<Entidad> execute() {
        return this.entidadRepository.getAll();
    }
}
