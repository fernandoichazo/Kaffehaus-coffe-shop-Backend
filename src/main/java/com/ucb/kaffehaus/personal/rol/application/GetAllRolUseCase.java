package com.ucb.kaffehaus.personal.rol.application;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ucb.kaffehaus.personal.rol.domain.Rol;
import com.ucb.kaffehaus.personal.rol.domain.RolRepository;

@Service
@Transactional(readOnly = true)
public class GetAllRolUseCase {

    private final RolRepository rolRepository;

    public GetAllRolUseCase(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    public List<Rol> execute() {
        return this.rolRepository.getAll();
    }
}
