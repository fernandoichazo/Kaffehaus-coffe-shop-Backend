package com.ucb.kaffehaus.personal.proveedor.application;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ucb.kaffehaus.personal.entidad.domain.Entidad;
import com.ucb.kaffehaus.personal.entidad.domain.EntidadRepository;
import com.ucb.kaffehaus.personal.persona.domain.Persona;
import com.ucb.kaffehaus.personal.persona.domain.PersonaRepository;
import com.ucb.kaffehaus.personal.proveedor.application.dto.UpdateProveedorRequest;
import com.ucb.kaffehaus.personal.proveedor.domain.Proveedor;
import com.ucb.kaffehaus.personal.proveedor.domain.ProveedorRepository;
import com.ucb.kaffehaus.shared.application.error.CustomException;

@Service
@Transactional
public class UpdateProveedorUseCase {

    private final ProveedorRepository proveedorRepository;
    private final PersonaRepository personaRepository;
    private final EntidadRepository entidadRepository;

    public UpdateProveedorUseCase(
            ProveedorRepository proveedorRepository,
            PersonaRepository personaRepository,
            EntidadRepository entidadRepository) {
        this.proveedorRepository = proveedorRepository;
        this.personaRepository = personaRepository;
        this.entidadRepository = entidadRepository;
    }

    public Optional<Proveedor> execute(UUID id, UpdateProveedorRequest request) {
        request.validate();

        Persona persona = null;
        if (request.getPersonaId() != null) {
            persona = this.personaRepository.findOne(request.getPersonaId())
                    .orElseThrow(() -> CustomException.notFound("No se encontro persona con id " + request.getPersonaId()));
        }

        Entidad entidad = null;
        if (request.getEntidadId() != null) {
            entidad = this.entidadRepository.findOne(request.getEntidadId())
                    .orElseThrow(() -> CustomException.notFound("No se encontro entidad con id " + request.getEntidadId()));
        }

        Proveedor proveedorToUpdate = Proveedor.restore(id, persona, entidad, false);
        return this.proveedorRepository.update(id, proveedorToUpdate);
    }
}
