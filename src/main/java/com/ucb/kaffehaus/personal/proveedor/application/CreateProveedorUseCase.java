package com.ucb.kaffehaus.personal.proveedor.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ucb.kaffehaus.personal.entidad.domain.Entidad;
import com.ucb.kaffehaus.personal.entidad.domain.EntidadRepository;
import com.ucb.kaffehaus.personal.persona.domain.Persona;
import com.ucb.kaffehaus.personal.persona.domain.PersonaRepository;
import com.ucb.kaffehaus.personal.proveedor.application.dto.CreateProveedorRequest;
import com.ucb.kaffehaus.personal.proveedor.domain.Proveedor;
import com.ucb.kaffehaus.personal.proveedor.domain.ProveedorRepository;
import com.ucb.kaffehaus.shared.application.error.CustomException;

@Service
@Transactional
public class CreateProveedorUseCase {

    private final ProveedorRepository proveedorRepository;
    private final PersonaRepository personaRepository;
    private final EntidadRepository entidadRepository;

    public CreateProveedorUseCase(
            ProveedorRepository proveedorRepository,
            PersonaRepository personaRepository,
            EntidadRepository entidadRepository) {
        this.proveedorRepository = proveedorRepository;
        this.personaRepository = personaRepository;
        this.entidadRepository = entidadRepository;
    }

    public Proveedor execute(CreateProveedorRequest request) {
        request.validate();

        Persona persona = this.personaRepository.findOne(request.getPersonaId())
                .orElseThrow(() -> CustomException.notFound("No se encontro persona con id " + request.getPersonaId()));

        Entidad entidad = null;
        if (request.getEntidadId() != null) {
            entidad = this.entidadRepository.findOne(request.getEntidadId())
                    .orElseThrow(() -> CustomException.notFound("No se encontro entidad con id " + request.getEntidadId()));
        }

        Proveedor proveedor = Proveedor.create(persona, entidad);
        return this.proveedorRepository.save(proveedor);
    }
}
