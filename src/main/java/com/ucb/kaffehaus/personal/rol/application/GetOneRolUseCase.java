package com.ucb.kaffehaus.personal.rol.application;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ucb.kaffehaus.personal.rol.domain.Rol;
import com.ucb.kaffehaus.personal.rol.domain.RolRepository;

@Service
@Transactional(readOnly = true)
public class GetOneRolUseCase {

    private final RolRepository rolRepository;

    public GetOneRolUseCase(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    public Optional<Rol> execute(UUID id) {
        return this.rolRepository.findOne(id)
                .filter(rol -> !rol.isBorrado());
    }
}
