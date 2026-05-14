package com.ucb.kaffehaus.personal.rol.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ucb.kaffehaus.personal.rol.application.dto.CreateRolRequest;
import com.ucb.kaffehaus.personal.rol.domain.Rol;
import com.ucb.kaffehaus.personal.rol.domain.RolRepository;

@Service
@Transactional
public class CreateRolUseCase {

    private final RolRepository rolRepository;

    public CreateRolUseCase(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    public Rol execute(CreateRolRequest request) {
        request.validate();

        Rol rol = Rol.create(request.getNombre());
        return this.rolRepository.save(rol);
    }
}
