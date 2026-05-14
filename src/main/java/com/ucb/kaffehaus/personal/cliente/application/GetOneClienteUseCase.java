package com.ucb.kaffehaus.personal.cliente.application;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ucb.kaffehaus.personal.cliente.domain.Cliente;
import com.ucb.kaffehaus.personal.cliente.domain.ClienteRepository;

@Service
@Transactional(readOnly = true)
public class GetOneClienteUseCase {

    private final ClienteRepository clienteRepository;

    public GetOneClienteUseCase(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Optional<Cliente> execute(int id) {
        return this.clienteRepository.findOne(id)
                .filter(cliente -> !cliente.isBorrado());
    }
}
