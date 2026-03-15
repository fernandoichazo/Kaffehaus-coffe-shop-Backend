package com.ucb.kaffehaus.personal.cliente.infrastructure;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ucb.kaffehaus.personal.cliente.domain.Cliente;
import com.ucb.kaffehaus.personal.cliente.domain.ClienteDatasource;

@Service
public class ClienteDatasourcePostgres implements ClienteDatasource{

    @Override
    public Cliente save(Cliente cliente) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public Optional<Cliente> update(int Id, Cliente cliente) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

}
