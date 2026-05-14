package com.ucb.kaffehaus.personal.entidad.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ucb.kaffehaus.personal.entidad.domain.EntidadRepository;

@Service
@Transactional
public class DeleteEntidadUseCase {

    private final EntidadRepository entidadRepository;

    public DeleteEntidadUseCase(EntidadRepository entidadRepository) {
        this.entidadRepository = entidadRepository;
    }

    public boolean execute(int id) {
        return this.entidadRepository.deleteOne(id);
    }
}
