package com.ucb.kaffehaus.inventario.lote.application;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ucb.kaffehaus.inventario.lote.domain.LoteRepository;

@Service
@Transactional
public class DeleteLoteUseCase {

    private final LoteRepository loteRepository;

    public DeleteLoteUseCase(LoteRepository loteRepository) {
        this.loteRepository = loteRepository;
    }

    public boolean execute(UUID id) {
        return this.loteRepository.deleteOne(id);
    }
}
