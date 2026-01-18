package com.estudiospringboot.finanzasapp.infrastructure.adapter.in.web.usuario;

import java.util.stream.Collectors;

import com.estudiospringboot.finanzasapp.domain.model.Usuario;

public class UsuarioMapper {

    public static UsuarioResponse toResponse(Usuario usuario){
        return new UsuarioResponse(
        usuario.getId(), 
        usuario.getNombre(), 
        usuario.getEmail(), 
        usuario.isAutorizado(), 
        usuario.getRoles().stream().map(Enum::name).collect(Collectors.toSet()));
    }

}
