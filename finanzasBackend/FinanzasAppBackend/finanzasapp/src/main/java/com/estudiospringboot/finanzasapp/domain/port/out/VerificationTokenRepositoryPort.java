package com.estudiospringboot.finanzasapp.domain.port.out;

import java.util.Optional;

import com.estudiospringboot.finanzasapp.domain.model.token.EmailVerificationToken;

public interface VerificationTokenRepositoryPort {

    void guardar(EmailVerificationToken token);

    Optional<EmailVerificationToken> encuentraPorToken(String token);

    void eliminar(String token);
}
