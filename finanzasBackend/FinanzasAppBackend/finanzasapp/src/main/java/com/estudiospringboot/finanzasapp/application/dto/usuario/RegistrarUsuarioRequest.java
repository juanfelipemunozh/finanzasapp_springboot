package com.estudiospringboot.finanzasapp.application.dto.usuario;

public class RegistrarUsuarioRequest {

    private String nombre;
    private String email;
    private String clave;
    
    public RegistrarUsuarioRequest(String nombre, String email, String clave) {
        this.nombre = nombre;
        this.email = email;
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getClave() {
        return clave;
    }

    

}
