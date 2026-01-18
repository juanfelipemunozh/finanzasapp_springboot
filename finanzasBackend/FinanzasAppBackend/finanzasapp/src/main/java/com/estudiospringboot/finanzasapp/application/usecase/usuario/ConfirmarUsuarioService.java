package com.estudiospringboot.finanzasapp.application.usecase.usuario;

import com.estudiospringboot.finanzasapp.domain.model.token.EmailVerificationToken;
import com.estudiospringboot.finanzasapp.domain.port.out.UsuarioRepositoryPort;
import com.estudiospringboot.finanzasapp.domain.port.out.VerificationTokenRepositoryPort;

public class ConfirmarUsuarioService {

    private final UsuarioRepositoryPort usuarioRepository;
    private final VerificationTokenRepositoryPort tokenRepository;

    public ConfirmarUsuarioService(UsuarioRepositoryPort usuarioRepository,
            VerificationTokenRepositoryPort tokenRepository) {
        this.usuarioRepository = usuarioRepository;
        this.tokenRepository = tokenRepository;
    }

    public void confirmar(String token) {
        EmailVerificationToken verificacionToken = tokenRepository.encuentraPorToken(token)
                .orElseThrow(() -> new IllegalArgumentException("Token no válido"));

        if (verificacionToken.isExpired()) {
            throw new IllegalArgumentException("El token ha expirado");
        }

        usuarioRepository.obtenerUsuarioPorId(verificacionToken.getUsuarioId()).ifPresent(usuario -> {
            usuario.autorizado();
            usuarioRepository.guardarUsuario(usuario);
        });

        tokenRepository.eliminar(token);
    }

}
