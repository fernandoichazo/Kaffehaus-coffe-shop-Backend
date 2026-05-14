package com.ucb.kaffehaus.personal.cliente.infrastructure;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ucb.kaffehaus.personal.cliente.domain.Cliente;
import com.ucb.kaffehaus.personal.cliente.domain.ClienteDatasource;
import com.ucb.kaffehaus.personal.cliente.domain.ClienteRepository;

@Service
public class ClienteRepositoryImpl implements ClienteRepository{

    private final ClienteDatasource datasource;

    public ClienteRepositoryImpl(ClienteDatasource datasource) {
        this.datasource = datasource;
    }

    @Override
    public Cliente save(Cliente cliente) {
        return this.datasource.save(cliente);
    }

    @Override
    public Optional<Cliente> update(UUID Id, Cliente cliente) {
        return this.datasource.update(Id, cliente);
    }

    @Override
    public List<Cliente> getAll() {
        return this.datasource.getAll();
    }

    @Override
    public Optional<Cliente> findOne(UUID Id) {
        return this.datasource.findOne(Id);
    }

    @Override
    public boolean deleteOne(UUID Id) {
        return this.datasource.deleteOne(Id);
    }

}
