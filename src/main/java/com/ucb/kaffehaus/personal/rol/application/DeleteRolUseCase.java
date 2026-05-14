package com.ucb.kaffehaus.personal.rol.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ucb.kaffehaus.personal.rol.domain.RolRepository;

@Service
@Transactional
public class DeleteRolUseCase {

    private final RolRepository rolRepository;

    public DeleteRolUseCase(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    public boolean execute(int id) {
        return this.rolRepository.deleteOne(id);
    }
}
