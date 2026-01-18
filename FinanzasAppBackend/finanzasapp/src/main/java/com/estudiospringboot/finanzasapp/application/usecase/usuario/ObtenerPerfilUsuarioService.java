package com.estudiospringboot.finanzasapp.application.usecase.usuario;

import org.springframework.stereotype.Service;

import com.estudiospringboot.finanzasapp.domain.model.Usuario;
import com.estudiospringboot.finanzasapp.domain.port.out.UsuarioRepositoryPort;
import com.estudiospringboot.finanzasapp.infrastructure.security.UsuarioAutenticado;

@Service
public class ObtenerPerfilUsuarioService {

    private final UsuarioRepositoryPort usuarioRepository;

    public ObtenerPerfilUsuarioService(UsuarioRepositoryPort usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario ejecutar(){
        String correo = UsuarioAutenticado.obtenerEmail();

        return usuarioRepository.obtenerUsuarioPorEmail(correo)
        .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }
}
