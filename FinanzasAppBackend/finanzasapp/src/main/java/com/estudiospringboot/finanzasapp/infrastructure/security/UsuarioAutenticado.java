package com.estudiospringboot.finanzasapp.infrastructure.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class UsuarioAutenticado {

    public static String obtenerEmail(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        return auth.getName();
    }

}
