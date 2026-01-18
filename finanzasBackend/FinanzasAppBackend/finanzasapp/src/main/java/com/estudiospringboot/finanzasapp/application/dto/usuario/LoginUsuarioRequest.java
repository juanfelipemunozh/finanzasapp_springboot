package com.estudiospringboot.finanzasapp.application.dto.usuario;

public class LoginUsuarioRequest {

    private String correo;
    private String clave;

    public LoginUsuarioRequest(String correo, String clave) {
        this.correo = correo;
        this.clave = clave;
    }

    public String getCorreo() {
        return correo;
    }

    public String getClave() {
        return clave;
    }

}
