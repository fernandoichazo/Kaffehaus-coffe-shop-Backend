package com.ucb.kaffehaus.personal.cliente.domain;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClienteRepository {
    Cliente save(Cliente cliente);
    Optional<Cliente> update(UUID Id, Cliente cliente);
    List<Cliente> getAll();
    Optional<Cliente> findOne(UUID Id);
    boolean deleteOne(UUID Id);
}
