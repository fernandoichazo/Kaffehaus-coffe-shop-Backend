package com.ucb.kaffehaus.personal.cliente.domain;
import java.util.List;
import java.util.Optional;

public interface ClienteRepository {
    Cliente save(Cliente cliente);
    Optional<Cliente> update(int Id, Cliente cliente);
    List<Cliente> getAll();
    Optional<Cliente> findOne(int Id);
    boolean deleteOne(int Id);
}
