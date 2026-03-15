package com.ucb.kaffehaus.personal.cliente.domain;
import java.util.Optional;

public interface ClienteDatasource {
    Cliente save(Cliente cliente);
    Optional<Cliente> update(int Id, Cliente cliente);
}
