package com.ucb.kaffehaus.inventario.lote.application;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ucb.kaffehaus.inventario.lote.application.dto.UpdateLoteRequest;
import com.ucb.kaffehaus.inventario.lote.domain.Lote;
import com.ucb.kaffehaus.inventario.lote.domain.LoteRepository;

@Service
@Transactional
public class UpdateLoteUseCase {

    private final LoteRepository loteRepository;

    public UpdateLoteUseCase(LoteRepository loteRepository) {
        this.loteRepository = loteRepository;
    }

    public Optional<Lote> execute(UUID id, UpdateLoteRequest request) {
        request.validate();

        Lote loteToUpdate = Lote.restore(
                id,
                request.getFechaCompra(),
                request.getProveedorId(),
                request.getTotal(),
                false);

        return this.loteRepository.update(id, loteToUpdate);
    }
}
