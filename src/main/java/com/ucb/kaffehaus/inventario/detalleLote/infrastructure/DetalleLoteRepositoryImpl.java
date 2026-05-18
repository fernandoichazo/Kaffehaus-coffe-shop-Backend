package com.ucb.kaffehaus.inventario.detalleLote.infrastructure;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ucb.kaffehaus.inventario.detalleLote.domain.DetalleLote;
import com.ucb.kaffehaus.inventario.detalleLote.domain.DetalleLoteDatasource;
import com.ucb.kaffehaus.inventario.detalleLote.domain.DetalleLoteRepository;

@Service
public class DetalleLoteRepositoryImpl implements DetalleLoteRepository {

    private final DetalleLoteDatasource datasource;

    public DetalleLoteRepositoryImpl(DetalleLoteDatasource datasource) {
        this.datasource = datasource;
    }

    @Override
    public DetalleLote save(DetalleLote detalleLote) {
        return this.datasource.save(detalleLote);
    }

    @Override
    public Optional<DetalleLote> update(UUID id, DetalleLote detalleLote) {
        return this.datasource.update(id, detalleLote);
    }

    @Override
    public List<DetalleLote> getAll() {
        return this.datasource.getAll();
    }

    @Override
    public Optional<DetalleLote> findOne(UUID id) {
        return this.datasource.findOne(id);
    }

    @Override
    public boolean deleteOne(UUID id) {
        return this.datasource.deleteOne(id);
    }
}
