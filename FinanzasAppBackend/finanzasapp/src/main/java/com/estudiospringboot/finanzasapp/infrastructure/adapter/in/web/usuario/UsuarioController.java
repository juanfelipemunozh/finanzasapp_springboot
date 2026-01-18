package com.estudiospringboot.finanzasapp.infrastructure.adapter.in.web.usuario;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estudiospringboot.finanzasapp.application.usecase.usuario.ObtenerPerfilUsuarioService;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final ObtenerPerfilUsuarioService obtenerPerfil;

    public UsuarioController(ObtenerPerfilUsuarioService obtenerPerfil){
        this.obtenerPerfil = obtenerPerfil;
    }

    @PreAuthorize("hasAnyRole('USUARIO','ADMIN')")
    @GetMapping("/perfil")
    public UsuarioResponse perfil() {
        return UsuarioMapper.toResponse(obtenerPerfil.ejecutar());
    }

    @GetMapping("/yo")
    public Object yo(Authentication authentication){
        return authentication;
    }
}
