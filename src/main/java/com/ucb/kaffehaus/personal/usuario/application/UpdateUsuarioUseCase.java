package com.ucb.kaffehaus.personal.usuario.application;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ucb.kaffehaus.personal.persona.domain.Persona;
import com.ucb.kaffehaus.personal.persona.domain.PersonaRepository;
import com.ucb.kaffehaus.personal.rol.domain.Rol;
import com.ucb.kaffehaus.personal.rol.domain.RolRepository;
import com.ucb.kaffehaus.personal.usuario.application.dto.UpdateUsuarioRequest;
import com.ucb.kaffehaus.personal.usuario.domain.Usuario;
import com.ucb.kaffehaus.personal.usuario.domain.UsuarioRepository;
import com.ucb.kaffehaus.shared.application.error.CustomException;

@Service
@Transactional
public class UpdateUsuarioUseCase {

    private final UsuarioRepository usuarioRepository;
    private final PersonaRepository personaRepository;
    private final RolRepository rolRepository;

    public UpdateUsuarioUseCase(
            UsuarioRepository usuarioRepository,
            PersonaRepository personaRepository,
            RolRepository rolRepository) {
        this.usuarioRepository = usuarioRepository;
        this.personaRepository = personaRepository;
        this.rolRepository = rolRepository;
    }

    public Optional<Usuario> execute(int id, UpdateUsuarioRequest request) {
        request.validate();

        Persona persona = null;
        if (request.getPersonaId() != null) {
            persona = this.personaRepository.findOne(request.getPersonaId())
                    .orElseThrow(() -> CustomException.notFound("No se encontro persona con id " + request.getPersonaId()));
        }

        Rol rol = null;
        if (request.getRolId() != null) {
            rol = this.rolRepository.findOne(request.getRolId())
                    .orElseThrow(() -> CustomException.notFound("No se encontro rol con id " + request.getRolId()));
        }

        Usuario usuarioToUpdate = Usuario.restore(
                id,
                persona,
                rol,
                request.getCorreo(),
                request.getContrasena());

        return this.usuarioRepository.update(id, usuarioToUpdate);
    }
}
