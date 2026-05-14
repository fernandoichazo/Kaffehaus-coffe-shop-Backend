package com.ucb.kaffehaus.personal.cliente.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ucb.kaffehaus.personal.cliente.domain.ClienteRepository;

@Service
@Transactional
public class DeleteClienteUseCase {

    private final ClienteRepository clienteRepository;

    public DeleteClienteUseCase(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public boolean execute(int id) {
        return this.clienteRepository.deleteOne(id);
    }
}
