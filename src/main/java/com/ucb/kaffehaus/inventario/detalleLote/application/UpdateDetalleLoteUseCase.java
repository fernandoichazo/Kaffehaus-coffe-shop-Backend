package com.ucb.kaffehaus.inventario.detalleLote.application;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ucb.kaffehaus.inventario.detalleLote.application.dto.UpdateDetalleLoteRequest;
import com.ucb.kaffehaus.inventario.detalleLote.domain.DetalleLote;
import com.ucb.kaffehaus.inventario.detalleLote.domain.DetalleLoteRepository;

@Service
@Transactional
public class UpdateDetalleLoteUseCase {

    private final DetalleLoteRepository detalleLoteRepository;

    public UpdateDetalleLoteUseCase(DetalleLoteRepository detalleLoteRepository) {
        this.detalleLoteRepository = detalleLoteRepository;
    }

    public Optional<DetalleLote> execute(UUID id, UpdateDetalleLoteRequest request) {
        request.validate();

        DetalleLote detalleLoteToUpdate = DetalleLote.restore(
                id,
                request.getLoteId(),
                request.getProductoId(),
                request.getCantidad(),
                request.getPrecioUnitario(),
                null,
                request.getFechaAcabado(),
                false);

        return this.detalleLoteRepository.update(id, detalleLoteToUpdate);
    }
}
