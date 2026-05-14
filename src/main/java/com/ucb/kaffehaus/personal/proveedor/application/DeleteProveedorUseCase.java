package com.ucb.kaffehaus.personal.proveedor.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import com.ucb.kaffehaus.personal.proveedor.domain.ProveedorRepository;

@Service
@Transactional
public class DeleteProveedorUseCase {

    private final ProveedorRepository proveedorRepository;

    public DeleteProveedorUseCase(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }

    public boolean execute(UUID id) {
        return this.proveedorRepository.deleteOne(id);
    }
}
