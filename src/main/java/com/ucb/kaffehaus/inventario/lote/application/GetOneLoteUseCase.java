package com.ucb.kaffehaus.inventario.lote.application;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ucb.kaffehaus.inventario.lote.domain.Lote;
import com.ucb.kaffehaus.inventario.lote.domain.LoteRepository;

@Service
@Transactional(readOnly = true)
public class GetOneLoteUseCase {

    private final LoteRepository loteRepository;

    public GetOneLoteUseCase(LoteRepository loteRepository) {
        this.loteRepository = loteRepository;
    }

    public Optional<Lote> execute(UUID id) {
        return this.loteRepository.findOne(id)
                .filter(lote -> !lote.isBorrado());
    }
}
