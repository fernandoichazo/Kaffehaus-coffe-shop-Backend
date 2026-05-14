package com.ucb.kaffehaus.personal.usuario.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ucb.kaffehaus.personal.persona.domain.Persona;
import com.ucb.kaffehaus.personal.persona.domain.PersonaRepository;
import com.ucb.kaffehaus.personal.rol.domain.Rol;
import com.ucb.kaffehaus.personal.rol.domain.RolRepository;
import com.ucb.kaffehaus.personal.usuario.application.dto.CreateUsuarioRequest;
import com.ucb.kaffehaus.personal.usuario.domain.Usuario;
import com.ucb.kaffehaus.personal.usuario.domain.UsuarioRepository;
import com.ucb.kaffehaus.shared.application.error.CustomException;

@Service
@Transactional
public class CreateUsuarioUseCase {

    private final UsuarioRepository usuarioRepository;
    private final PersonaRepository personaRepository;
    private final RolRepository rolRepository;

    public CreateUsuarioUseCase(
            UsuarioRepository usuarioRepository,
            PersonaRepository personaRepository,
            RolRepository rolRepository) {
        this.usuarioRepository = usuarioRepository;
        this.personaRepository = personaRepository;
        this.rolRepository = rolRepository;
    }

    public Usuario execute(CreateUsuarioRequest request) {
        request.validate();
        Persona persona;
        if (request.getPersonaId() == null) {
            persona = Persona.create(
                request.getNombre(),
                request.getApellidos(),
                request.getTelefono(),
                request.getDni());
            persona = this.personaRepository.save(persona);
        } else {
            persona = this.personaRepository.findOne(request.getPersonaId())
                .orElseThrow(() -> CustomException.notFound("No se encontro persona con id " + request.getPersonaId()));
        }

        Rol rol = this.rolRepository.findOne(request.getRolId())
                .orElseThrow(() -> CustomException.notFound("No se encontro rol con id " + request.getRolId()));

        Usuario usuario = Usuario.create(persona, rol, request.getCorreo(), request.getContrasena());
        return this.usuarioRepository.save(usuario);
    }
}
