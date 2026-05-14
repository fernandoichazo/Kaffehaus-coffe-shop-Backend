package com.ucb.kaffehaus.personal.usuario.infrastructure;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ucb.kaffehaus.personal.persona.domain.Persona;
import com.ucb.kaffehaus.personal.persona.infrastructure.PersonaEntity;
import com.ucb.kaffehaus.personal.persona.infrastructure.PersonaJpaRepository;
import com.ucb.kaffehaus.personal.rol.domain.Rol;
import com.ucb.kaffehaus.personal.rol.infrastructure.RolEntity;
import com.ucb.kaffehaus.personal.rol.infrastructure.RolJpaRepository;
import com.ucb.kaffehaus.personal.usuario.domain.Usuario;
import com.ucb.kaffehaus.personal.usuario.domain.UsuarioDatasource;
import com.ucb.kaffehaus.shared.application.error.CustomException;

@Service
public class UsuarioDatasourcePostgres implements UsuarioDatasource {

    private final UsuarioJpaRepository usuarioJpaRepository;
    private final PersonaJpaRepository personaJpaRepository;
    private final RolJpaRepository rolJpaRepository;

    public UsuarioDatasourcePostgres(
            UsuarioJpaRepository usuarioJpaRepository,
            PersonaJpaRepository personaJpaRepository,
            RolJpaRepository rolJpaRepository) {
        this.usuarioJpaRepository = usuarioJpaRepository;
        this.personaJpaRepository = personaJpaRepository;
        this.rolJpaRepository = rolJpaRepository;
    }

    @Override
    public Usuario save(Usuario usuario) {
        PersonaEntity personaEntity = this.getPersonaEntity(usuario.getPersona().getId());
        RolEntity rolEntity = this.getRolEntity(usuario.getRol().getId());

        UsuarioEntity entity = new UsuarioEntity(
                personaEntity,
                rolEntity,
                usuario.getCorreo(),
                usuario.getContrasena());

        UsuarioEntity saved = this.usuarioJpaRepository.save(entity);
        usuario.setId(saved.getId());
        return usuario;
    }

    @Override
    public Optional<Usuario> update(UUID Id, Usuario usuario) {
        UsuarioEntity entity = this.usuarioJpaRepository.findById(Id)
                .orElseThrow(() -> CustomException.notFound("No se encontro usuario con id " + Id));

        if (usuario.getPersona() != null && usuario.getPersona().getId() != null) {
            PersonaEntity personaEntity = this.getPersonaEntity(usuario.getPersona().getId());
            entity.setPersona(personaEntity);
        }

        if (usuario.getRol() != null && usuario.getRol().getId() != null) {
            RolEntity rolEntity = this.getRolEntity(usuario.getRol().getId());
            entity.setRol(rolEntity);
        }

        if (usuario.getCorreo() != null) {
            entity.setCorreo(usuario.getCorreo());
        }

        if (usuario.getContrasena() != null) {
            entity.setContrasena(usuario.getContrasena());
        }

        UsuarioEntity updated = this.usuarioJpaRepository.save(entity);
        return Optional.of(this.toDomain(updated));
    }

    @Override
    public List<Usuario> getAll() {
        return this.usuarioJpaRepository.findAll().stream()
                .filter(entity -> !entity.getPersona().isBorrado())
                .filter(entity -> entity.getRol() == null || !entity.getRol().isBorrado())
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Usuario> findOne(UUID Id) {
        UsuarioEntity entity = this.usuarioJpaRepository.findById(Id)
                .orElseThrow(() -> CustomException.notFound("No se encontro usuario con id " + Id));

        if (entity.getPersona().isBorrado()) {
            throw CustomException.notFound("No se encontro usuario con id " + Id);
        }

        if (entity.getRol() != null && entity.getRol().isBorrado()) {
            throw CustomException.notFound("No se encontro usuario con id " + Id);
        }

        return Optional.of(this.toDomain(entity));
    }

    @Override
    public boolean deleteOne(UUID Id) {
        UsuarioEntity entity = this.usuarioJpaRepository.findById(Id)
                .orElseThrow(() -> CustomException.notFound("No se encontro usuario con id " + Id));

        this.usuarioJpaRepository.delete(entity);
        return true;
    }

    private Usuario toDomain(UsuarioEntity entity) {
        PersonaEntity personaEntity = entity.getPersona();
        Persona persona = Persona.restore(
                personaEntity.getId(),
                personaEntity.getNombre(),
                personaEntity.getApellidos(),
                personaEntity.getTelefono(),
                personaEntity.getDni(),
                personaEntity.isBorrado());

        Rol rol = null;
        if (entity.getRol() != null) {
            RolEntity rolEntity = entity.getRol();
            rol = Rol.restore(rolEntity.getId(), rolEntity.getNombre(), rolEntity.isBorrado());
        }

        return Usuario.restore(entity.getId(), persona, rol, entity.getCorreo(), entity.getContrasena());
    }

    private PersonaEntity getPersonaEntity(UUID personaId) {
        PersonaEntity personaEntity = this.personaJpaRepository.findById(personaId)
                .orElseThrow(() -> CustomException.notFound("No se encontro persona con id " + personaId));

        if (personaEntity.isBorrado()) {
            throw CustomException.notFound("No se encontro persona con id " + personaId);
        }

        return personaEntity;
    }

    private RolEntity getRolEntity(UUID rolId) {
        RolEntity rolEntity = this.rolJpaRepository.findById(rolId)
                .orElseThrow(() -> CustomException.notFound("No se encontro rol con id " + rolId));

        if (rolEntity.isBorrado()) {
            throw CustomException.notFound("No se encontro rol con id " + rolId);
        }

        return rolEntity;
    }
}
