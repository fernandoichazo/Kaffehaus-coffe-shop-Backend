package com.ucb.kaffehaus.inventario.detalleLote.application;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ucb.kaffehaus.inventario.detalleLote.domain.DetalleLote;
import com.ucb.kaffehaus.inventario.detalleLote.domain.DetalleLoteRepository;

@Service
@Transactional(readOnly = true)
public class GetOneDetalleLoteUseCase {

    private final DetalleLoteRepository detalleLoteRepository;

    public GetOneDetalleLoteUseCase(DetalleLoteRepository detalleLoteRepository) {
        this.detalleLoteRepository = detalleLoteRepository;
    }

    public Optional<DetalleLote> execute(UUID id) {
        return this.detalleLoteRepository.findOne(id)
                .filter(detalle -> !detalle.isBorrado());
    }
}
