package com.ucb.kaffehaus.personal.rol.application;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ucb.kaffehaus.personal.rol.application.dto.UpdateRolRequest;
import com.ucb.kaffehaus.personal.rol.domain.Rol;
import com.ucb.kaffehaus.personal.rol.domain.RolRepository;

@Service
@Transactional
public class UpdateRolUseCase {

    private final RolRepository rolRepository;

    public UpdateRolUseCase(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    public Optional<Rol> execute(UUID id, UpdateRolRequest request) {
        request.validate();

        Rol rolToUpdate = Rol.restore(id, request.getNombre(), false);
        return this.rolRepository.update(id, rolToUpdate);
    }
}
