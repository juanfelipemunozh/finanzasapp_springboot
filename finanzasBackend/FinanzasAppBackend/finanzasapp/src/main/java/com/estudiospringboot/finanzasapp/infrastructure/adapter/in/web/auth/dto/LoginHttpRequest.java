package com.estudiospringboot.finanzasapp.infrastructure.adapter.in.web.auth.dto;

public class LoginHttpRequest {

    private String correo;
    private String clave;

    public String getCorreo() {
        return correo;
    }

    public String getClave() {
        return clave;
    }

}
