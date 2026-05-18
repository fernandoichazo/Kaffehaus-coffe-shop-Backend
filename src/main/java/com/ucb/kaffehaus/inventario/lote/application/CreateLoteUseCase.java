package com.ucb.kaffehaus.inventario.lote.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ucb.kaffehaus.inventario.lote.application.dto.CreateLoteRequest;
import com.ucb.kaffehaus.inventario.lote.domain.Lote;
import com.ucb.kaffehaus.inventario.lote.domain.LoteRepository;

@Service
@Transactional
public class CreateLoteUseCase {

    private final LoteRepository loteRepository;

    public CreateLoteUseCase(LoteRepository loteRepository) {
        this.loteRepository = loteRepository;
    }

    public Lote execute(CreateLoteRequest request) {
        request.validate();
        Lote lote = Lote.create(request.getFechaCompra(), request.getProveedorId(), request.getTotal());
        return this.loteRepository.save(lote);
    }
}
