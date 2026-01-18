package com.estudiospringboot.finanzasapp.infrastructure.adapter.in.web.usuario;

import java.util.Set;
import java.util.UUID;

public class UsuarioResponse {

    private UUID id;
    private String nombre;
    private String correo;
    private boolean autorizado;
    private Set<String> roles;

    public UsuarioResponse(UUID id, String nombre, String correo, boolean autorizado, Set<String> roles) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.autorizado = autorizado;
        this.roles = roles;
    }

    public UUID getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public boolean isAutorizado() {
        return autorizado;
    }

    public Set<String> getRoles() {
        return roles;
    }

    
    

}
