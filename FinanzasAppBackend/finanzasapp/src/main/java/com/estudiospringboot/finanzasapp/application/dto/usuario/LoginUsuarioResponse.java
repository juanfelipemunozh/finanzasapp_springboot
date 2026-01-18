package com.estudiospringboot.finanzasapp.application.dto.usuario;

public class LoginUsuarioResponse {

    private String token;

    public LoginUsuarioResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

}
