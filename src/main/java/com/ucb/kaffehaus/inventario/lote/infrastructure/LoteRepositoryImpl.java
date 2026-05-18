package com.ucb.kaffehaus.inventario.lote.infrastructure;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ucb.kaffehaus.inventario.lote.domain.Lote;
import com.ucb.kaffehaus.inventario.lote.domain.LoteDatasource;
import com.ucb.kaffehaus.inventario.lote.domain.LoteRepository;

@Service
public class LoteRepositoryImpl implements LoteRepository {

    private final LoteDatasource datasource;

    public LoteRepositoryImpl(LoteDatasource datasource) {
        this.datasource = datasource;
    }

    @Override
    public Lote save(Lote lote) {
        return this.datasource.save(lote);
    }

    @Override
    public Optional<Lote> update(UUID id, Lote lote) {
        return this.datasource.update(id, lote);
    }

    @Override
    public List<Lote> getAll() {
        return this.datasource.getAll();
    }

    @Override
    public Optional<Lote> findOne(UUID id) {
        return this.datasource.findOne(id);
    }

    @Override
    public boolean deleteOne(UUID id) {
        return this.datasource.deleteOne(id);
    }
}
