package com.ucb.kaffehaus.personal.cliente.application;

import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ucb.kaffehaus.personal.cliente.domain.Cliente;
import com.ucb.kaffehaus.personal.cliente.domain.ClienteRepository;
import com.ucb.kaffehaus.personal.persona.domain.Persona;
import com.ucb.kaffehaus.shared.application.dto.PaginatedResponse;
import com.ucb.kaffehaus.shared.application.dto.PaginationDto;

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

    public PaginatedResponse<List<Cliente>> execute(PaginationDto pagination) {
        List<Cliente> clientes = this.clienteRepository.getAll();
        String search = pagination != null ? pagination.getSearch() : null;
        if (search != null && !search.isBlank()) {
            String normalized = search.trim().toLowerCase(Locale.ROOT);
            clientes = clientes.stream()
                    .filter(cliente -> matchesSearch(cliente, normalized))
                    .toList();
        }

        long total = clientes.size();
        int skip = pagination != null && pagination.getSkip() != null
                ? Math.max(0, pagination.getSkip())
                : 0;
        Integer limit = pagination != null ? pagination.getLimit() : null;

        Stream<Cliente> stream = clientes.stream().skip(skip);
        if (limit != null && limit > 0) {
            stream = stream.limit(limit);
        }

        List<Cliente> paged = stream.toList();
        return PaginatedResponse.of(paged, total);
    }

    private boolean matchesSearch(Cliente cliente, String search) {
        Persona persona = cliente.getPersona();
        if (persona == null) {
            return false;
        }

        return containsIgnoreCase(persona.getNombre(), search)
                || containsIgnoreCase(persona.getApellidos(), search)
                || containsIgnoreCase(persona.getTelefono(), search)
                || containsIgnoreCase(persona.getDni(), search);
    }

    private boolean containsIgnoreCase(String value, String search) {
        if (value == null) {
            return false;
        }
        return value.toLowerCase(Locale.ROOT).contains(search);
    }
}
