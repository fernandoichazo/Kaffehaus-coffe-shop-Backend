package com.ucb.kaffehaus.inventario.detalleLote.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ucb.kaffehaus.inventario.detalleLote.application.dto.CreateDetalleLoteRequest;
import com.ucb.kaffehaus.inventario.detalleLote.domain.DetalleLote;
import com.ucb.kaffehaus.inventario.detalleLote.domain.DetalleLoteRepository;

@Service
@Transactional
public class CreateDetalleLoteUseCase {

    private final DetalleLoteRepository detalleLoteRepository;

    public CreateDetalleLoteUseCase(DetalleLoteRepository detalleLoteRepository) {
        this.detalleLoteRepository = detalleLoteRepository;
    }

    public DetalleLote execute(CreateDetalleLoteRequest request) {
        request.validate();
        DetalleLote detalleLote = DetalleLote.create(
                request.getLoteId(),
                request.getProductoId(),
                request.getCantidad(),
                request.getPrecioUnitario(),
                request.getFechaAcabado());
        return this.detalleLoteRepository.save(detalleLote);
    }
}
