package com.ucb.kaffehaus.inventario.lote.application;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ucb.kaffehaus.inventario.lote.domain.Lote;
import com.ucb.kaffehaus.inventario.lote.domain.LoteRepository;

@Service
@Transactional(readOnly = true)
public class GetAllLoteUseCase {

    private final LoteRepository loteRepository;

    public GetAllLoteUseCase(LoteRepository loteRepository) {
        this.loteRepository = loteRepository;
    }

    public List<Lote> execute() {
        return this.loteRepository.getAll();
    }
}
