package com.ucb.kaffehaus.inventario.detalleLote.application;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ucb.kaffehaus.inventario.detalleLote.domain.DetalleLoteRepository;

@Service
@Transactional
public class DeleteDetalleLoteUseCase {

    private final DetalleLoteRepository detalleLoteRepository;

    public DeleteDetalleLoteUseCase(DetalleLoteRepository detalleLoteRepository) {
        this.detalleLoteRepository = detalleLoteRepository;
    }

    public boolean execute(UUID id) {
        return this.detalleLoteRepository.deleteOne(id);
    }
}
