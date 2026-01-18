package com.estudiospringboot.finanzasapp.infrastructure.adapter.in.web.auth.dto;

public class LoginHttpResponse {

    private String token;

    public LoginHttpResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

}
