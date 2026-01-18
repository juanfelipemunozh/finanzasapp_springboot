package com.estudiospringboot.finanzasapp.infrastructure.persistence.mapper;

import com.estudiospringboot.finanzasapp.domain.model.token.EmailVerificationToken;
import com.estudiospringboot.finanzasapp.infrastructure.persistence.entity.VerificationTokenEntity;

public class VerificationTokenMapper {

    public static VerificationTokenEntity toEntity(EmailVerificationToken token){
        VerificationTokenEntity entity = new VerificationTokenEntity();
        entity.setToken(token.getToken());
        entity.setUsuarioId(token.getUsuarioId());
        entity.setFechaExpiracion(token.getFechaExpiracion());
        return entity;
    }

    public static EmailVerificationToken toDomain(VerificationTokenEntity entity){
        return new EmailVerificationToken(
                entity.getToken(),
                entity.getUsuarioId(),
                entity.getFechaExpiracion()
        );
    }

}
