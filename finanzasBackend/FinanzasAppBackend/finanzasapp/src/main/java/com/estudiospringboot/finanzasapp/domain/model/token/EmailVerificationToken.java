package com.estudiospringboot.finanzasapp.domain.model.token;

import java.time.LocalDateTime;
import java.util.UUID;

public class EmailVerificationToken {

    private final String token;
    private final UUID usuarioId;
    private final LocalDateTime fechaExpiracion;

    public EmailVerificationToken(String token, UUID usuarioId, LocalDateTime fechaExpiracion) {
        this.token = token;
        this.usuarioId = usuarioId;
        this.fechaExpiracion = fechaExpiracion;
    }

    public static EmailVerificationToken crearToken(UUID usuarioId){
        return new EmailVerificationToken(
                UUID.randomUUID().toString(),
                usuarioId,
                LocalDateTime.now().plusHours(24)
        );
    }
        
    public boolean isExpired() {
        return LocalDateTime.now().isAfter(fechaExpiracion);
    }

    public String getToken() {
        return token;
    }

    public UUID getUsuarioId() {
        return usuarioId;
    }

    public LocalDateTime getFechaExpiracion() {
        return fechaExpiracion;
    }
}
