package com.estudiospringboot.finanzasapp.domain.port.out;

import com.estudiospringboot.finanzasapp.domain.model.Usuario;

public interface JwtTokenPort {

    String generarToken(Usuario usuario); 

    String generarTokenString(String correo);

    String extraerNombreUsuario(String token);

    boolean esTokenValido(String token, Usuario usuario);
} 