package com.ucb.kaffehaus.personal.proveedor.application;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ucb.kaffehaus.personal.proveedor.domain.Proveedor;
import com.ucb.kaffehaus.personal.proveedor.domain.ProveedorRepository;

@Service
@Transactional(readOnly = true)
public class GetAllProveedorUseCase {

    private final ProveedorRepository proveedorRepository;

    public GetAllProveedorUseCase(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }

    public List<Proveedor> execute() {
        return this.proveedorRepository.getAll();
    }
}
