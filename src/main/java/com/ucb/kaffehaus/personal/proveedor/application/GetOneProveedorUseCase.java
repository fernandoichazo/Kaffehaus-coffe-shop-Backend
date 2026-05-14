package com.ucb.kaffehaus.personal.proveedor.application;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ucb.kaffehaus.personal.proveedor.domain.Proveedor;
import com.ucb.kaffehaus.personal.proveedor.domain.ProveedorRepository;

@Service
@Transactional(readOnly = true)
public class GetOneProveedorUseCase {

    private final ProveedorRepository proveedorRepository;

    public GetOneProveedorUseCase(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }

    public Optional<Proveedor> execute(UUID id) {
        return this.proveedorRepository.findOne(id)
                .filter(proveedor -> !proveedor.isBorrado());
    }
}
