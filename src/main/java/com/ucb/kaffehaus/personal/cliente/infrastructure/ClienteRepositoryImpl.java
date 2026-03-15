package com.ucb.kaffehaus.personal.cliente.infrastructure;

import java.util.Optional;

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
    public Optional<Cliente> update(int Id, Cliente cliente) {
        return this.datasource.update(Id, cliente);
    }

}
