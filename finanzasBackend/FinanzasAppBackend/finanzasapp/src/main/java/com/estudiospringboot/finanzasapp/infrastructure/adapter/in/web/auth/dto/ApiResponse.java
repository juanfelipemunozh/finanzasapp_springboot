package com.estudiospringboot.finanzasapp.infrastructure.adapter.in.web.auth.dto;

public class ApiResponse {

    private String mensaje;

    public ApiResponse(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

}
