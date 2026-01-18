package com.estudiospringboot.finanzasapp.application.usecase.usuario;

import com.estudiospringboot.finanzasapp.application.dto.usuario.LoginUsuarioRequest;
import com.estudiospringboot.finanzasapp.application.dto.usuario.LoginUsuarioResponse;
import com.estudiospringboot.finanzasapp.domain.model.Usuario;
import com.estudiospringboot.finanzasapp.domain.port.out.JwtTokenPort;
import com.estudiospringboot.finanzasapp.domain.port.out.LoginUsuarioUseCase;
import com.estudiospringboot.finanzasapp.domain.port.out.UsuarioRepositoryPort;
import com.estudiospringboot.finanzasapp.domain.service.EncriptadorClaveService;

public class LoginUsuarioService implements LoginUsuarioUseCase {

    private final UsuarioRepositoryPort usuarioRepository;
    private final EncriptadorClaveService encriptadorClaveService;
    private final JwtTokenPort jwtTokenPort;

    public LoginUsuarioService(UsuarioRepositoryPort usuarioRepository,
            EncriptadorClaveService encriptadorClaveService,
            JwtTokenPort jwtTokenPort
) {
        this.usuarioRepository = usuarioRepository;
        this.encriptadorClaveService = encriptadorClaveService;
        this.jwtTokenPort = jwtTokenPort;

    }

    public LoginUsuarioResponse login(LoginUsuarioRequest request) {

        Usuario usuario = usuarioRepository.obtenerUsuarioPorEmail(request.getCorreo())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!usuario.isAutorizado()) {
            throw new RuntimeException("User not confirmed");
        }

        if (!encriptadorClaveService.coinciden(
                request.getClave(),
                usuario.getClave())) {
            throw new RuntimeException("Clave incorrecta");
        }

        String token = jwtTokenPort.generarToken(usuario);

        return new LoginUsuarioResponse(token);

    }

    @Override
    public String login(String correo, String clave) {
        
        Usuario usuario = usuarioRepository.obtenerUsuarioPorEmail(correo)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!encriptadorClaveService.coinciden(
                clave,
                usuario.getClave())) {
            throw new RuntimeException("Clave incorrecta");
        }

        return jwtTokenPort.generarToken(usuario);
    }

}
