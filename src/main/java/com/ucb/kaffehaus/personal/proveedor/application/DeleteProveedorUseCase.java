package com.ucb.kaffehaus.personal.proveedor.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ucb.kaffehaus.personal.proveedor.domain.ProveedorRepository;

@Service
@Transactional
public class DeleteProveedorUseCase {

    private final ProveedorRepository proveedorRepository;

    public DeleteProveedorUseCase(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }

    public boolean execute(int id) {
        return this.proveedorRepository.deleteOne(id);
    }
}
