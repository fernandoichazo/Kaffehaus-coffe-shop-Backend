package com.ucb.kaffehaus.personal.cliente.application;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ucb.kaffehaus.personal.cliente.domain.Cliente;
import com.ucb.kaffehaus.personal.cliente.domain.ClienteRepository;

@Service
@Transactional(readOnly = true)
public class GetAllClienteUseCase {

    private final ClienteRepository clienteRepository;

    public GetAllClienteUseCase(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> execute() {
        return this.clienteRepository.getAll();
    }
}
