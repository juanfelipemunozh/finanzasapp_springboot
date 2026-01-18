package com.estudiospringboot.finanzasapp.infrastructure.persistence.mapper;

import com.estudiospringboot.finanzasapp.domain.model.Usuario;
import com.estudiospringboot.finanzasapp.infrastructure.persistence.entity.UsuarioEntity;

public class UsuarioPersistenceMapper {

    public static UsuarioEntity toEntiy(Usuario usuario) {
        UsuarioEntity entity = new UsuarioEntity();
        entity.setId(usuario.getId());
        entity.setNombre(usuario.getNombre());
        entity.setEmail(usuario.getEmail());
        entity.setClave(usuario.getClave());
        entity.setAutorizado(usuario.isAutorizado());
        entity.setProvider(usuario.getProvider());
        entity.setRoles(usuario.getRoles());
        return entity;        
    }

    public static Usuario toDomain(UsuarioEntity entity) {
        return new Usuario(
            entity.getId(),
            entity.getNombre(),
            entity.getEmail(),
            entity.getClave(),
            entity.isAutorizado(),
            entity.getProvider(),
            entity.getRoles()
        );
    }
}
