package com.estudiospringboot.finanzasapp.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.estudiospringboot.finanzasapp.application.usecase.usuario.ConfirmarUsuarioService;
import com.estudiospringboot.finanzasapp.application.usecase.usuario.LoginUsuarioService;
import com.estudiospringboot.finanzasapp.application.usecase.usuario.RegistrarUsuarioService;
import com.estudiospringboot.finanzasapp.domain.port.out.EnviarEmailPort;
import com.estudiospringboot.finanzasapp.domain.port.out.JwtTokenPort;
import com.estudiospringboot.finanzasapp.domain.port.out.UsuarioRepositoryPort;
import com.estudiospringboot.finanzasapp.domain.port.out.VerificationTokenRepositoryPort;
import com.estudiospringboot.finanzasapp.domain.service.EncriptadorClaveService;

@Configuration
public class UseCaseConfig {

    @Bean
    public RegistrarUsuarioService registrarUsuarioService(
            UsuarioRepositoryPort usuarioRepositoryPort,
            EncriptadorClaveService encriptadorClaveService,
            VerificationTokenRepositoryPort verificationTokenRepositoryPort,
            EnviarEmailPort enviarEmailPort) {

        return new RegistrarUsuarioService(
                usuarioRepositoryPort, encriptadorClaveService,
                verificationTokenRepositoryPort, enviarEmailPort);
    }

    @Bean
    public ConfirmarUsuarioService confirmarUsuarioService(
            UsuarioRepositoryPort usuarioRepositoryPort,
            VerificationTokenRepositoryPort verificationTokenRepositoryPort) {
        return new ConfirmarUsuarioService(
                usuarioRepositoryPort, verificationTokenRepositoryPort);
    }

    @Bean
    public LoginUsuarioService loginUsuarioService(
            UsuarioRepositoryPort usuarioRepositoryPort,
            EncriptadorClaveService encriptadorClaveService,
            JwtTokenPort jwtTokenPort
        ) {
        return new LoginUsuarioService(
                usuarioRepositoryPort, 
                encriptadorClaveService,
                jwtTokenPort);
    }
}
