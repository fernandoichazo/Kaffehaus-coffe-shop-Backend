package com.ucb.kaffehaus.inventario.detalleLote.application;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ucb.kaffehaus.inventario.detalleLote.domain.DetalleLote;
import com.ucb.kaffehaus.inventario.detalleLote.domain.DetalleLoteRepository;

@Service
@Transactional(readOnly = true)
public class GetAllDetalleLoteUseCase {

    private final DetalleLoteRepository detalleLoteRepository;

    public GetAllDetalleLoteUseCase(DetalleLoteRepository detalleLoteRepository) {
        this.detalleLoteRepository = detalleLoteRepository;
    }

    public List<DetalleLote> execute() {
        return this.detalleLoteRepository.getAll();
    }
}
